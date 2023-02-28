package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.security.RepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private RepositoryUserDetailsService userService;
    @GetMapping("/SignUp")
    public String getRegister(Model model){

        return "register";
    }

    @GetMapping("/SignUpError")
    public String getLoginError(Model model){
        return "SignUpError";
    }
}
