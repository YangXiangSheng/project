package com.springboot.demo.intercept;

import com.springboot.demo.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {
    //事情方法
    public boolean before();
    public void after();

    /**
     * 取代原有事件办法
     *
     * @throws IllegalAccessException
     * @param工nvocat工on一回调参数，可以通过它的proceed方法，回调原有事件*
     * @return原有事件返回对象女＠throwsInvocationTargetException *
     */
    public  Object around(Invocation invocation)
        throws InvocationTargetException,IllegalAccessException;
    //是否返回方法。事件没有发生异常执行
    public void afterReturning();
    //事后异常方法，当事件发生异常后执行
    public void afterThrowing();
    //是否使用around方法取代原有方法
    public boolean useAround();
}
