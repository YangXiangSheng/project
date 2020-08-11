package com.springboot.demomvc;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.springboot.demomvc", annotationClass = Mapper.class)
public class DemomvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomvcApplication.class, args);
    }

}
