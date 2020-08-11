package com.springboot.transactional.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.springboot.transactional.demo")
@MapperScan(basePackages = "com.springboot.transactional.demo.dao",annotationClass = Repository.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
@Autowired
    PlatformTransactionManager transactionManager=null;
    //使用后初始化方法，观察自动生成的事务管理器
    @PostConstruct
    public void viewTransactionManager(){
        //启动前加入断点观测
        System.out.println(transactionManager.getClass().getName());
    }
}
