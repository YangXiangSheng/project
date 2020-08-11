package com.springboot.demo.controller;

import com.springboot.demo.User;
import com.springboot.demo.service.UserService;
import com.springboot.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class AopTestController {
    @Autowired
    private UserService userService=null;

    @RequestMapping("/print")
    @ResponseBody
   /* public String hello(){
        return "hello";
    }*/
    public User printUser(Long id , String userName, String note){
        //User user=null;//出错情况
        User user=new User();
        user.setId(id);
        user.setUsername(userName);
        user.setNote(note);
        userService.printUser(user);
                //／／若user=null，则执行afterthrowing方法return user；／／加入断点
                return user;
    }
    /**
     * 增强
     */
    @RequestMapping("/vp")
    @ResponseBody
    public User validateAndPrint(Long id,String userName,String note){
       //User user=null;
        User user=new User();
        user.setId(id);
        user.setUsername(userName);
        user.setNote(note);
        //强制转换
        UserValidator userValidator=(UserValidator)userService;
        //验证用户是否为空
        if(userValidator.validate(user)){
            userService.printUser(user);
        }
        return user;
    }
    @RequestMapping("/manyAspects")
    @ResponseBody
    public String manyAspects(){
        userService.manyAspects();
        return "manyAspects";
    }
}
