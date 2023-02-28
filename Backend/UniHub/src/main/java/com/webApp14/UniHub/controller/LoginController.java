package com.webApp14.UniHub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/LogIn")
    public String getLogin(Model model){
        return "login";
    }

    @GetMapping("/LogInError")
    public String getLoginError(Model model){
        return "loginerror";
    }
}
