package com.vip.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class CachedRestController {
    @RequestMapping("")
    @ResponseBody  // 没有缓存 -> 304
    // 服务端和客户端没有形成默契(状态码)
    // HTTP协议，REST继承
    public String HelloWorld() { // 200/500/400
        return "Hello World";  // body -> Hello World
    }


    @RequestMapping("/cache") // Spring MVC 返回值处理
    public ResponseEntity<String> cache(
            @RequestParam(required = false, defaultValue = "false") boolean cached
    ) {
        ResponseEntity<String> result = null;
        if (cached) {
            result = new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
        } else {
            result = ResponseEntity.ok("Hello World!");
        }
        return result;
    }
}
