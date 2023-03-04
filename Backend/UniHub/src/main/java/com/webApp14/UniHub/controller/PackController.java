package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.PackRepository;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class PackController {
    @Autowired
    private PackRepository packRepository;

    @Autowired
    private UserRepository userRepository;

    Principal principalUser;

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


    @GetMapping("/packs")
    public String packs(Model model){
        List<Pack> packList = packRepository.findAll();
        model.addAttribute("packList", packList);
        return "packs";
    }
    @GetMapping("/packInfo/{id}")
    public String packInfo(@PathVariable("id") Long id, Model model) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pack id"));
        model.addAttribute("pack", pack);
        return "packInfo";
    }

    @PostMapping("/addPack/{id}")
    public String addPack(@PathVariable("id") Long id) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pack id"));
        Optional<User> optionalUser = userRepository.findByUsername(principalUser.getName());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.getPackList().add(pack);
            userRepository.save(user);
        }
        return "redirect:/packs";
    }



}
