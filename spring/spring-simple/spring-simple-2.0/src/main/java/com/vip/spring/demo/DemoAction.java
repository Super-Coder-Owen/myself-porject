package com.vip.spring.demo;


import com.vip.spring.demo.service.IDemoService;
import com.vip.spring.formework.annotation.Autowired;
import com.vip.spring.formework.annotation.Controller;
import com.vip.spring.formework.annotation.RequestMapping;
import com.vip.spring.formework.annotation.RequestParam;
import com.vip.spring.formework.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
public class DemoAction {
    @Autowired
    private IDemoService demoService;

    @RequestMapping("/test")
    public ModelAndView test(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("name") String name) {
        String result = demoService.getName(name);
        System.err.println(result);
       /* try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @RequestMapping("/test1*")
    public ModelAndView test1(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("name") String name) {
        String result = demoService.getName(name);
        return out(response, result);
    }

    @RequestMapping("/index.html")
    public ModelAndView index(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("name") String name) {
        String result = demoService.getName(name);
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("data", result);
        model.put("token", "123456");
        return new ModelAndView("index.html",model);

    }

    private ModelAndView out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
