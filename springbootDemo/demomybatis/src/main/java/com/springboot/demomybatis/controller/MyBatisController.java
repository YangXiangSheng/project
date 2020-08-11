package com.springboot.demomybatis.controller;

import com.springboot.demomybatis.pojo.User;
import com.springboot.demomybatis.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyBatisController {
    @Autowired
    private MyBatisUserService myBatisUserService=null;
    @RequestMapping("getUser")
    @ResponseBody
    public User getUser(Long id){
        return myBatisUserService.getUser(id);
    }
    @RequestMapping("getUser1")
    @ResponseBody
    public String hello(){
        return"hello";
    }
}
