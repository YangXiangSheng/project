package com.springboot.demorest;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.springboot.demorest",annotationClass = Mapper.class)
public class DemorestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemorestApplication.class, args);
    }

}
