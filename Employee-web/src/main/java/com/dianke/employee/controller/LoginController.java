package com.dianke.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value = {"/","login"})
    public String login(){
        return "login";
    }
    @RequestMapping("logging")
    public String logging(){
       return "registered";
    }

    @RequestMapping("homepage")
    public String homepage(){
        return "homepage";
    }

    @RequestMapping("dropOut")
    public String dropOut(HttpServletResponse resp,String username,String password){
        if(username != null){
            Cookie nameCookie = new Cookie("username",username);
            Cookie pwdCookie = new Cookie("password",password);
            nameCookie.setMaxAge(0);
            pwdCookie.setMaxAge(0);
            resp.addCookie(nameCookie);
            resp.addCookie(pwdCookie);
        }
        return "redirect:login";
    }
}
