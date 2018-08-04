package com.vip.spring.demo.service.impl;

import com.vip.spring.demo.service.IDemoService;
import com.vip.spring.formework.annotation.Service;

/**
 *
 */
@Service
public class IDemoServiceImpl implements IDemoService {
    @Override
    public String getName(String name) {
        return "Hello World! " + name;
    }
}
