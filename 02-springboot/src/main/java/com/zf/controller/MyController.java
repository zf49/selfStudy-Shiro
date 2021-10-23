package com.zf.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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



      @RequestMapping("/login")
     public String login(String username,String password,Model model){
        
         // 获取当前用户

         Subject subject = SecurityUtils.getSubject();
         // 封装用户的登陆数据
         UsernamePasswordToken token = new UsernamePasswordToken(username, password);

         try{
             subject.login(token); //执行登陆的方法，如果没有异常，说明可以了

        


             return "index";
         }catch (UnknownAccountException e){//用户名不存在
               model.addAttribute("msg","用户名不存在");
               return "/user/login";
         } catch (IncorrectCredentialsException e){  //密码不存在
             model.addAttribute("msg","密码不存在");
             return "/user/login";
         }

     }



     @RequestMapping("/noauth")
     @ResponseBody
     public String unauthorized(){
        return "未授权无法访问此页面";
     }


    

}
