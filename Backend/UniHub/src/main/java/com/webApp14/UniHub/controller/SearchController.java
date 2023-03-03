package com.webApp14.UniHub.controller;


import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    private PackRepository packRepository;

    @GetMapping("/search")
    public String search(Model model, @RequestParam("query") String keyword){
        Optional <Pack> packs = packRepository.findByPackDescription_longContaining(keyword);
        model.addAttribute("packs", packs);
        model.addAttribute("keyword", keyword);
        return "search_results";
    }
}
