package com.springboot.demomvc.service.impl;


import com.springboot.demomvc.dao.UserDao;
import com.springboot.demomvc.pojo.User;
import com.springboot.demomvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

 @Autowired
 private UserDao userDao=null;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName, note);
    }

}
