package com.springboot.demospringmvc.dao;

import com.springboot.demospringmvc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    User getUser(Long id);

    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);

    int insertUser(User user);
}
