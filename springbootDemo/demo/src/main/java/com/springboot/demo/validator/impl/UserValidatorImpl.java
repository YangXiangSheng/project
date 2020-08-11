package com.springboot.demo.validator.impl;

import com.springboot.demo.User;
import com.springboot.demo.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("引入新的接口："+UserValidator.class.getSimpleName());
        return user!=null;
    }
}
