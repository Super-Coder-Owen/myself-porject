package com.vip.first.controller;

import com.vip.first.domain.User;
import com.vip.first.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring mvc 方式
 */
@RestController
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 保存使用Spring Web MVC
    @PostMapping("user/save")
    public User saveUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return user;
    }
}
