package com.springboot.demo.config;

import com.springboot.demo.aspect.MyAspect;
import com.springboot.demo.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ={"com.springboot.demo.*"},basePackageClasses = UserServiceImpl.class)
public class AspectConfig {
    @Bean(name="myAspect")
    public MyAspect initMyAspect(){
        return new MyAspect();
    }
}
