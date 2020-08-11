package com.springboot.demo.aspect;

import com.springboot.demo.User;
import com.springboot.demo.validator.UserValidator;
import com.springboot.demo.validator.impl.UserValidatorImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class MyAspect {
    /**
     * 原始办法
     */
    /*
    @Before("execution(* com.springboot.demo.service.impl.UserServiceImpl.printUser(..))")
    public void before(){
        System.out.println("before .....");
    }
    @After("execution(* com.springboot.demo.service.impl.UserServiceImpl.printUser(..)))")
    public void after(){
        System.out.println("after .....");
    }
    @AfterReturning("execution(* com.springboot.demo.service.impl.UserServiceImpl.printUser(..))")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }
    @AfterThrowing("execution(* com.springboot.demo.service.impl.UserServiceImpl.printUser(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }*/
    /**
     * + 号代表该增强服务类 UserServiceImpl 的所有子类，而 UserServiceImpl 没有子类，只有本身，所以需要去掉这个 +
     */
    @DeclareParents(value= "com.springboot.demo.service.impl.UserServiceImpl", defaultImpl= UserValidatorImpl.class)
   public UserValidator userValidator;

   @Pointcut("execution(* com.springboot.demo.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut() {
    }
/*
/**
前置通知获得参数
 */
    @Before("pointCut() && args(user)")
    public void beforeParam( User user) {
        System.out.println("before ......");
    }
/*
   @Before("pointCut()")
    public void before() {
        System.out.println("before ......");
    }
*/
   @After("pointCut()")
    public void after() {
        System.out.println("after ......");
    }


    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }


    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before......");
        jp.proceed();
        System.out.println("around after......");
    }
}

