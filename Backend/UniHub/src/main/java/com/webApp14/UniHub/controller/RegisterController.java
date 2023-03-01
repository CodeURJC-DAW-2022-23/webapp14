package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.UserRepository;
import com.webApp14.UniHub.security.RepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private User currentUser=null;


    @GetMapping("/SignUp")
    public String getRegister(Model model){
        return "register";
    }

    @PostMapping("/UserSignUp")
    public ModelAndView processRegister(Model model, @RequestParam String username, @RequestParam String email, @RequestParam String password){
        User user = new User(username, email, passwordEncoder.encode(password), false);

        Optional <User> tryUser = userRepository.findByUsername(user.getUsername());
        Optional <User> tryEmail = userRepository.findByEmail(user.getEmail());

        if (!tryUser.isPresent() && !tryEmail.isPresent()){
            userRepository.save(user);
            if(!user.getAdmin()) {
                model.addAttribute("loggedUser", true);
                model.addAttribute("logged",true);
            }else if(user.getAdmin()){
                model.addAttribute("admin", true);
                model.addAttribute("logged",true);
            }
            currentUser = user;
            return new ModelAndView(new RedirectView("/", true));
        }else {
            return new ModelAndView(new RedirectView("/error", true));
        }
    }
}
