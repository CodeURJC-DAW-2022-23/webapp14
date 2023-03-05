package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.PackRepository;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class PackController {

    // Attributes
    @Autowired
    private PackRepository packRepository;

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
    // Method to show with AJAX the next 2 packs from packList
    @GetMapping("/packs")
    public String packs(Model model, @RequestParam(defaultValue = "0") int page) {
        // Number page and number of item retrievals
        Pageable pageable = PageRequest.of(page, 4);
        // Makes a pageable query
        Page<Pack> packPage = packRepository.findAll(pageable);
        // Gets the data from the current page
        List<Pack> packList = packPage.getContent();
        // Adds the data to the model
        model.addAttribute("packList", packList);
        model.addAttribute("currentPage", packPage.getNumber());
        model.addAttribute("totalPages", packPage.getTotalPages());
        // Prints the view
        if (page == 0) {
            return "packs";
        } else {
            return "morePacks";
        }
    }

    // It loads the packInfo.html with the information of a selected pack
    @GetMapping("/packInfo/{id}")
    public String packInfo(@PathVariable("id") Long id, Model model) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pack id"));
        model.addAttribute("pack", pack);
        return "packInfo";
    }

    // Method to add a package selected to the current user packList so they can check it on clientArea later on
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
