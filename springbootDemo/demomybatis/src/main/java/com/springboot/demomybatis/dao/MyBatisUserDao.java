package com.springboot.demomybatis.dao;

import com.springboot.demomybatis.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatisUserDao {
    public User getUser(Long id);
}
