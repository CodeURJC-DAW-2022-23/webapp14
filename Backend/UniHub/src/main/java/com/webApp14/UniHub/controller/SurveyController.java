package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@Controller
public class SurveyController {

    // Attributes
    @Autowired
    private UserRepository userRepository;

    Principal principalUser;

    // Method to insert the user credentials on the html model
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        principalUser = principal;
        if(principal != null) {
            Optional<User> optionalUser = userRepository.findByUsername(principalUser.getName());
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                byte[] imageBytes = user.getImage();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                model.addAttribute("imageHeader", base64Image);
            }
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
        } else {
            model.addAttribute("logged", false);
        }
    }

    // Loads surveys.html
    @GetMapping("/surveys")
    public String survey(Model model){
        return "surveys";
    }
}
