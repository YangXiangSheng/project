package com.springboot.transactional.demo.dao;

import com.springboot.transactional.demo.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User getUser(Long id);
    int insertUser(User user);
}
