package com.zf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping("/test")
    @ResponseBody
    public String test1(){
        return "Test";
    }

    @RequestMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("msg","hello, shiro");

        return "index";
    }



    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }


    @RequestMapping("/tologin")
    public String toLogin(){
      return "user/login";
    }



}
