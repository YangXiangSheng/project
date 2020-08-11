package com.springboot.demo.main;

import com.springboot.demo.intercept.MyInterceptor;
import com.springboot.demo.proxy.ProxyBean;
import com.springboot.demo.service.HelloService;
import com.springboot.demo.service.impl.HelloServiceImpl;

public class AopMain {
    public static void main(String[] args) {
        testProxy();
    }

    private static void testProxy() {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("zhangsan");
        System.out.println("\n###############name is null!!#############\n");
        proxy.sayHello(null);
    }

}
