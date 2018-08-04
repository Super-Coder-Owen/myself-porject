package com.vip.spring.demo;


import com.vip.spring.demo.service.IDemoService;
import com.vip.spring.formework.annotation.Autowired;
import com.vip.spring.formework.annotation.Controller;
import com.vip.spring.formework.annotation.RequestMapping;
import com.vip.spring.formework.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Controller
public class DemoAction {
    @Autowired
    private IDemoService demoService;

    @RequestMapping("/test")
    public void test(
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
    }
}
