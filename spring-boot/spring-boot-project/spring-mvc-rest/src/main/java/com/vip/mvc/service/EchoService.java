package com.vip.mvc.service;

import com.vip.mvc.annotation.TransactionalService;

/**
 *
 */
@TransactionalService(value = "echoService-2018",txName = "myTxName") // @Service bean + @Transactional
public class EchoService {
    public void echo(String message) {
        System.out.println(message);
    }
}
