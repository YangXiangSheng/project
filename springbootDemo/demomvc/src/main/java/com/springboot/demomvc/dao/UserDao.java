package com.springboot.demomvc.dao;

import com.springboot.demomvc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    User getUser(Long id);

    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
}
