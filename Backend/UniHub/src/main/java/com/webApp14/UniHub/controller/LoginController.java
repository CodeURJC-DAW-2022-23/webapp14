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


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepositoryUserDetailsService userService;


    @GetMapping("/LogIn")
    public String getLogin(Model model){
        return "login";
    }

    /*@PostMapping ("/processFormLogin")
    public ModelAndView processForm(Model model, @RequestParam String username, @RequestParam String password){
        Optional <User> tryUser = userRepository.findByUsername(username);
        if (tryUser.isPresent()) {
            if (passwordEncoder.matches(password, tryUser.get().getPassword())){
                return new ModelAndView(new RedirectView("/", true));
            }else{
                return new ModelAndView(new RedirectView("/error", true));
            }

        }else{
            return new ModelAndView(new RedirectView("/error", true));
        }
    }*/


    @GetMapping("/LogInError")
    public String getLoginError(Model model){
        return "error";
    }

}
