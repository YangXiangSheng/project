package com.springboot.transactional.demo.service;

import com.springboot.transactional.demo.pojo.User;

public interface UserService {
    //获取用户信息
    public User getUser(Long id);
    //新增用户
    public int insertUser(User user);
}
