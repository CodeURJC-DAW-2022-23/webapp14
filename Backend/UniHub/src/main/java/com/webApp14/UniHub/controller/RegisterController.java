package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.UserRepository;
import com.webApp14.UniHub.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.io.*;
import java.util.Optional;

@Controller
public class RegisterController {

    // Attributes
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;


    // Shows the register.html for a user to register on it
    @GetMapping("/SignUp")
    public String getRegister(Model model){
        return "register";
    }

    /* When a user fully fills out the form, it created a new user with all the data required and sends a message
    to the user's designated email address*/
    @PostMapping("/UserSignUp")
    public ModelAndView processRegister(Model model, @RequestParam String username, @RequestParam String email, @RequestParam String password) throws IOException {
        User user = new User(username, email, passwordEncoder.encode(password), "USER");

        File file = new File("src/main/resources/static/img/Profile-Pics/profile_img.png");

        // Read the image file into a byte array
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();

        user.setImage(imageBytes);

        Optional <User> tryUser = userRepository.findByUsername(user.getUsername());
        Optional <User> tryEmail = userRepository.findByEmail(user.getEmail());

        if (!tryUser.isPresent() && !tryEmail.isPresent()){
            userRepository.save(user);

            String subject = "Bienvenido a nuestra aplicación";
            String content = "Gracias por registrarte en nuestra aplicación. Esperamos que disfrutes usándola.";

            emailService.sendEmail(user.getEmail(), subject, content);
            return new ModelAndView(new RedirectView("/", true));
        }else {
            return new ModelAndView(new RedirectView("/error", true));
        }
    }

}
