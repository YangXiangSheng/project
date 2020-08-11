package com.springboot.demo.validator;

import com.springboot.demo.User;

public interface UserValidator {
    //检测对象是否为空
    public boolean validate(User user);
}
