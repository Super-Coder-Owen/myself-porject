package com.vip.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 */
@Controller
public class IndexController {
    // 第一种Model方式
    /*
    @GetMapping({"/", ""})
    public String index(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }*/

    // 第2种@ModelAttribute方式
   /* @GetMapping({"/", ""})
    public String index(Model model) {
        return "index";
    }

    @ModelAttribute(name = "message")
    public String message() {
        return "Hello World123!";
    }*/

    // 使用自定义的对象
    @GetMapping({"/", ""})
    public String index(Model model) {
        model.addAttribute("string",new StringUtil());
        return "index";
    }

    @ModelAttribute(name = "message")
    public String message() {
        return "Hello World456!";
    }

    public static class StringUtil{
        public StringUtil(){

        }

        public boolean isNotBlack(String value){
            return StringUtils.hasText(value);
        }
    }
}
