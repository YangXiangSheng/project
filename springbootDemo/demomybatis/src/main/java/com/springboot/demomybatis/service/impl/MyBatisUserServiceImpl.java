package com.springboot.demomybatis.service.impl;

import com.springboot.demomybatis.dao.MyBatisUserDao;
import com.springboot.demomybatis.pojo.User;
import com.springboot.demomybatis.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {
    @Autowired
    private MyBatisUserDao myBatisUserDao=null;
    @Override
    public User getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }
}
