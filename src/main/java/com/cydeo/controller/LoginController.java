package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/","/login"})
    public String login(){
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}