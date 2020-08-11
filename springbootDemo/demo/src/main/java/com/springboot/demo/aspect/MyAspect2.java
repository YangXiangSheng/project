package com.springboot.demo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class MyAspect2 {
    @Pointcut("execution(* com.springboot.demo.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects() {
    }
    @Before("manyAspects()")
    public void before(){
        System.out.println("MyAspect2 before.....");
    }
    @After("manyAspects()")
    public void after(){
        System.out.println("MtAspect2 after .....");
    }
    @After("manyAspects()")
    public void afterReturning(){
        System.out.println("MtAspect2 afterReturning .....");
    }
}
