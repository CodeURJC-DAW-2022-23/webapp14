package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.PackRepository;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PackController {
    @Autowired
    private PackRepository packRepository;

    @Autowired
    private UserRepository userRepository;

    private User currentUser=null;


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

    @GetMapping("/packInfo/{id}/purchase")
    public String packPurchase(@PathVariable("id") Long id, Model model) {
        Pack pack = packRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pack id"));
        model.addAttribute("pack", pack);
        return "purchase";
    }


}
