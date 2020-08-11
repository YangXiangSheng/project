package com.springboot.transactional.demo.service;

import com.springboot.transactional.demo.pojo.User;

import java.util.List;

public interface UserBatchService {
    public int insertUsers(List<User> userList);
    public int insertUser(List<User> userList);
}
