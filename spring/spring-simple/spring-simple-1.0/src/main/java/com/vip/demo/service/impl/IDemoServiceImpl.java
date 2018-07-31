package com.vip.demo.service.impl;

import com.vip.demo.service.IDemoService;
import com.vip.spring.annotation.Service;

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
