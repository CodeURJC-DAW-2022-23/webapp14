package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.security.RepositoryUserDetailsService;
import com.webApp14.UniHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {



    @Autowired
    private RepositoryUserDetailsService userService;

    @RequestMapping("/LogIn")
    public String getLogin(Model model){
        return "login";
    }

    @GetMapping("/LogInError")
    public String getLoginError(Model model){
        return "loginerror";
    }
}
