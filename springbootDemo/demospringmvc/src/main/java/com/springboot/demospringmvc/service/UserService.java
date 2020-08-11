package com.springboot.demospringmvc.service;

import com.springboot.demospringmvc.pojo.User;

import java.util.List;

public interface UserService {

    User getUser(Long id);

    List<User> findUsers(String userName, String note);

    int insertUser(User user);

}