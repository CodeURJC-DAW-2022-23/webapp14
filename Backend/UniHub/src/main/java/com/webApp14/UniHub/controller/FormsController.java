package com.webApp14.UniHub.controller;
import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.FormsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class FormsController {
    @Autowired
    private FormsRepository formsRepository;

    @GetMapping("/forms")
    public String forms(Model model){
        List<Forms> threadList = formsRepository.findAll();
        model.addAttribute("threadList", threadList);
        return "forms";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model){
        List<Forms> threadList = formsRepository.findAll();
        Forms forms = formsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pack id"));
        model.addAttribute("post", forms);
        return "post";
    }

    @GetMapping("/forms/formsMaker")
    public String formsMaker(Model model){
        return "formsMaker";
    }


}
