package com.springboot.demo.service.impl;

import com.springboot.demo.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
    if(name==null||name.trim()==""){
        throw new RuntimeException(("pARAMETER IS NULL !!"));
    }
    System.out.println("hello"+name);
    }
}
