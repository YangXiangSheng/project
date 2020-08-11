package com.springboot.demospringmvc.controller;

import com.springboot.demospringmvc.pojo.User;
import com.springboot.demospringmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/session")
//@SessionAttributes指定数据模型名称或者属性类型，保存到Session中
@SessionAttributes(names= {"user"}, types= Long.class)
public class SessionController {
    @Autowired
    private UserService userService=null;
    @GetMapping("/test")
    //@SessionAttribute从HttpSession中取出数据，填充控制器方法参数
    public String test(@SessionAttribute("id") Long id, Model model){
        //根据类型保存到session
        model.addAttribute("id_new",id);
        User user=userService.getUser(id);
        //根据名称保存到session
        model.addAttribute("user",user);
        return "session/test";
    }
}
