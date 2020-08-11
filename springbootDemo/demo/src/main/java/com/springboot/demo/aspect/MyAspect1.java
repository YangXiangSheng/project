package com.springboot.demo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)//指定执行顺序
public class MyAspect1 {
    @Pointcut("execution(* com.springboot.demo.service.impl.UserServiceImpl.manyAspects(..))")
    public void manyAspects() {
    }
    @Before("manyAspects()")
    public void before(){
        System.out.println("MyAspect1 before.....");
    }
    @After("manyAspects()")
    public void after(){
        System.out.println("MtAspect1 after .....");
    }
    @After("manyAspects()")
    public void afterReturning(){
        System.out.println("MtAspect1 afterReturning .....");
    }
}
