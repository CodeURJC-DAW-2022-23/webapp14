package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.FormsRepository;
import com.webApp14.UniHub.repository.PackRepository;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PackRepository packRepository;
    @Autowired
    FormsRepository formsRepository;

    Principal principalUser;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        principalUser = principal;
        if(principal != null) {
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
        } else {
            model.addAttribute("logged", false);
        }
    }
    
    @GetMapping(value={"/", "/main"})
    public String main(){
        return "main";
    }

    @GetMapping("/clientArea")
    public String clientArea(Model model, HttpServletRequest request){
        Optional<User> optionalUser = userRepository.findByUsername(principalUser.getName());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            List<Pack> userPacks = packRepository.findPacksByUser(user.getId());
            model.addAttribute("userPacks", userPacks);
            byte[] imageBytes = user.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            model.addAttribute("image", base64Image);
        }
        List<Forms> threadList = formsRepository.findAll();
        model.addAttribute("threads", threadList);
        model.addAttribute("user", principalUser.getName());
        return "clientArea";
    }

    @GetMapping("/deleteThread/{id}")
    public String deleteThread(@PathVariable("id") Long id) {
        formsRepository.deleteById(id);
        return "redirect:/clientArea";
    }

    @PostMapping("/upload/image")
    public String uploadImage(@RequestParam("image") MultipartFile imageFile, Model model) {
        User user = userRepository.findByUsername(principalUser.getName()).orElseThrow(() -> new IllegalArgumentException("Invalid user name"));
        try {
            user.setImage(imageFile.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/clientArea";
    }
}
