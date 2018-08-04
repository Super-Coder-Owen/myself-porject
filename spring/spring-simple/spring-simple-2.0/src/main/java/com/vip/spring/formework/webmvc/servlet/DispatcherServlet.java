package com.vip.spring.formework.webmvc.servlet;

import com.vip.spring.formework.annotation.Controller;
import com.vip.spring.formework.annotation.RequestMapping;
import com.vip.spring.formework.annotation.RequestParam;
import com.vip.spring.formework.context.ClassPathXmlApplicationContext;
import com.vip.spring.formework.webmvc.HandlerAdapter;
import com.vip.spring.formework.webmvc.HandlerMapping;
import com.vip.spring.formework.webmvc.ModelAndView;
import com.vip.spring.formework.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class DispatcherServlet extends HttpServlet {
    /*private Map<String, HandlerMapping> handlerMapping = new HashMap<>();
*/
    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new HashMap<>();

    private List<ViewResolver> viewResolvers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config.getInitParameter("contextConfigLocation").replace("classpath:", ""));

        // 初始化
        initStrategies(context);
    }

    private void initStrategies(ClassPathXmlApplicationContext context) {
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);

        // HandlerMapping用来保存Controller中配置的RequestMapping和Method的一个对应关系
        initHandlerMappings(context);
        // HandlerAdapters用来动态匹配Method参数，包括类型转换，动态赋值
        initHandlerAdapters(context);

        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);

        // ViewResolver用来实现动态模板解析
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        // 根据用户请求获取一个handler
        HandlerMapping hm = getHandler(req);
        if (null == hm) {
            resp.getWriter().write("404 Not Found!");
            return;
        }

        HandlerAdapter ha = getHandlerAdapter(hm);

        ModelAndView mv = ha.handler(req, resp, hm);

        processDispatchResult(resp, mv);
    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws IOException {
        if (null == mv) {
            return;
        }
        if (this.viewResolvers.isEmpty()) {
            return;
        }
        for (ViewResolver vr : this.viewResolvers) {
            if (!mv.getViewName().equals(vr.getViewName())) {
                continue;
            }
            String out = vr.viewResolver(mv);
            if (null != out) {
                resp.getWriter().write(out);
                break;
            }
        }
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping hm) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        return this.handlerAdapters.get(hm);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replace("/+", "/");

        for (HandlerMapping hm : this.handlerMappings) {
            Matcher matcher = hm.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            } else {
                return hm;
            }
        }
        return null;
    }

    /**
     * HandlerMapping用来保存Controller中配置的RequestMapping和Method的一个对应关系
     *
     * @param context
     */
    private void initHandlerMappings(ClassPathXmlApplicationContext context) {
        // 从容器中取得所有实例
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object instance = context.getBean(beanName);
            Class<?> clazz = instance.getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping rm = clazz.getAnnotation(RequestMapping.class);
                baseUrl = rm.value();
            }

            // 扫描所有的public方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping rm = method.getAnnotation(RequestMapping.class);
                String regex = ("/" + baseUrl + rm.value()).replaceAll("/+", "");
                Pattern pattern = Pattern.compile(regex);
                this.handlerMappings.add(new HandlerMapping(pattern, instance, method));
                System.out.println("Mapping->" + regex + ",method->" + method);
            }
        }
    }

    private void initHandlerAdapters(ClassPathXmlApplicationContext context) {
        // 在初始化阶段，将参数的名字和类型按照一定顺序保存下来，
        // 可以通过这些参数的index,挨个从数组中填值

        for (HandlerMapping hm : this.handlerMappings) {
            // 每个方法有一个参数列表  kry-value:参数名-位置
            Map<String, Integer> paramMapping = new HashMap<>();

            // 这里处理命名参数
            Annotation[][] pa = hm.getMethod().getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof RequestParam) {
                        String paramName = ((RequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            // 接下来处理非命名参数
            // 只处理Request和Response
            Class<?>[] paramTypes = hm.getMethod().getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }

            this.handlerAdapters.put(hm, new HandlerAdapter(paramMapping));

        }

    }

    private void initViewResolvers(ClassPathXmlApplicationContext context) {
        // 在页面输入http://localhost/first.html
        // 解决页面名字和模板文件关联的问题
        String templateRoot = context.getConfigContext().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        for (File file : templateRootDir.listFiles()) {
            this.viewResolvers.add(new ViewResolver(file.getName(), file));
        }
    }


    private void initThemeResolver(ClassPathXmlApplicationContext context) {
    }

    private void initLocaleResolver(ClassPathXmlApplicationContext context) {
    }

    private void initMultipartResolver(ClassPathXmlApplicationContext context) {
    }

    private void initFlashMapManager(ClassPathXmlApplicationContext context) {
    }

    private void initRequestToViewNameTranslator(ClassPathXmlApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(ClassPathXmlApplicationContext context) {
    }

}
