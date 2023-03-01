package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PackController {
    @Autowired
    private PackRepository packRepository;

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

    @GetMapping("/packs")
    public String packs(Model model, Pageable pageable) {
        int initialLimit = 2;
        int initialOffset = 0;
        // Get limited packs
        Page<Pack> packList = packRepository.findAll(pageable);
        boolean hasMorePacks = packRepository.count() > initialLimit;

        model.addAttribute("packList", packList.getContent());
        model.addAttribute("offset",  initialOffset);
        model.addAttribute("hasMorePacks", hasMorePacks);
        return "packs";
    }



    @GetMapping("/load-more-packs")
    public String loadMorePacks(Model model, @RequestParam("limit") int limit, @RequestParam("offset") int offset, Pageable pageable) {
        pageable = PageRequest.of(offset, limit);
        Page<Pack> packList = packRepository.findAll(pageable);
        boolean hasMorePacks = packRepository.count() > (offset + limit);
        model.addAttribute("packList", packList.getContent());
        model.addAttribute("offset",  offset);
        model.addAttribute("hasMorePacks", hasMorePacks);
        return "packs :: #pack-extra";
    }

}
