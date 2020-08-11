package com.springboot.demomybatis.service;

import com.springboot.demomybatis.pojo.User;

public interface MyBatisUserService {
    public User getUser(Long id);
}
