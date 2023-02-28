package com.webApp14.UniHub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/SignUp")
    public String getRegister(Model model){
        return "register";
    }
}
