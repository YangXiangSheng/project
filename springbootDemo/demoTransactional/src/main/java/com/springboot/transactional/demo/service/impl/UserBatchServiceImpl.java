package com.springboot.transactional.demo.service.impl;

import com.springboot.transactional.demo.dao.UserDao;
import com.springboot.transactional.demo.pojo.User;
import com.springboot.transactional.demo.service.UserBatchService;
import com.springboot.transactional.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBatchServiceImpl implements UserBatchService {
    @Autowired
    private UserService userService=null;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUsers(List<User> userList) {
        int count=0;
        for(User user:userList){
            count +=userService.insertUser(user);
        }
        return count;
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    public int insertUser(List<User> userList){
        int count=0;
        for(User user:userList){
            count +=userService.insertUser(user);
        }
        return count;
    }
}
