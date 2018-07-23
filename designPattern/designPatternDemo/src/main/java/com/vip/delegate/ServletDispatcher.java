package com.vip.delegate;

import com.vip.delegate.action.MemberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 委派模式
 */
public class ServletDispatcher {
    private List<Handler> handlerMapping = new ArrayList<Handler>();

    public ServletDispatcher() {
        Class<?> mClazz = MemberAction.class;
        try {
            this.handlerMapping.add(new Handler()
                    .setController(mClazz.newInstance())
                    .setMethod(mClazz.getMethod("getMember", new Class[]{String.class}))
                    .setUrl("/web/member"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doService(HttpServletRequest request, HttpServletResponse response) {
        this.doDispatch(request, response);
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        // 1.获取用户请求url
        // 如果按照j2ee标准，每个url对应一个servlet,url有浏览器输入
        String uri = request.getRequestURI();

        // 2.servlet拿到url后要做权衡
        // 根据用户请求的url，去找到对应的某一个java类方法

        // 3.通过拿到的url去handlerMapping（策略常量）
        Handler handler = null;
        for (Handler h : handlerMapping) {
            if (uri.equals(h.getUrl())) {
                handler = h;
                break;
            }
        }

        // 4.将具体的任务分发给method（通过反射调用其对应方法）
        Object obj = null;
        try {
            obj = handler.getMethod().invoke(handler.getController(), request.getParameter("id"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 5.获取到method的执行结果，通过response返回
        // response.getWriter().write();
    }

    public class Handler {
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
