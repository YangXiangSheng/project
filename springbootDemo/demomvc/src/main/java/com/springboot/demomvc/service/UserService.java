package com.springboot.demomvc.service;

import com.springboot.demomvc.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);

    List<User> findUsers(String userName, String note);
}
