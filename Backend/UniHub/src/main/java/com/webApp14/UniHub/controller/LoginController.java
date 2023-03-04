package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.UserRepository;
import com.webApp14.UniHub.security.RepositoryUserDetailsService;
import com.webApp14.UniHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
public class LoginController {

    // Attributes
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepositoryUserDetailsService userService;

    // Loads the login.html
    @GetMapping("/LogIn")
    public String getLogin(Model model){
        return "login";
    }

    // Returns the error.html page, notified when something goes wrong during navigation
    @GetMapping("/LogInError")
    public String getLoginError(Model model){
        return "error";
    }

}
