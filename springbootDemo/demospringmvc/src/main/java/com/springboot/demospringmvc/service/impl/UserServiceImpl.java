package com.springboot.demospringmvc.service.impl;

import com.springboot.demospringmvc.dao.UserDao;
import com.springboot.demospringmvc.pojo.User;
import com.springboot.demospringmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName, note);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

}
