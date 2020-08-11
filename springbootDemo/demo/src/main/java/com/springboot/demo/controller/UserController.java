package com.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String index(){
        return "Hello Spring Boot hello word hello";
    }
    @RequestMapping("/index1")
    public String index(Model model) {
        model.addAttribute("name", "jack");
        model.addAttribute("age", 20);
        model.addAttribute("info", "我是一个爱学习的好青年");
        return "index1";
    }
    @RequestMapping("/index2")
    public String index2(HttpServletRequest request){
        String id = request.getParameter("id");
        request.setAttribute("name",id);
        return "index1";
    }
}
