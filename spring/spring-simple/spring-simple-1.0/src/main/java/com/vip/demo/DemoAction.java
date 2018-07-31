package com.vip.demo;

import com.vip.demo.service.IDemoService;
import com.vip.spring.annotation.Autowired;
import com.vip.spring.annotation.Controller;
import com.vip.spring.annotation.RequestMapping;
import com.vip.spring.annotation.RequestParam;

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
