package com.webApp14.UniHub.controller;
import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.ThreadPics;
import com.webApp14.UniHub.repository.FormsRepository;
import com.webApp14.UniHub.repository.ThreadPicsRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class FormsController {
    @Autowired
    private FormsRepository formsRepository;

    @Autowired
    private ThreadPicsRepository threadPicsRepository;

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
        List<ThreadPics> threadPicsList = threadPicsRepository.findAll();
        model.addAttribute("threadPicsList", threadPicsList );
        return "formsMaker";
    }

    @PostMapping("/forms/formsMaker")
    public String handleFormSubmission(@RequestParam("title") String title,
                                       @RequestParam("subtitle") String subtitle,
                                       @RequestParam("description") String description,
                                       Model model,
                                       Authentication authentication) {

        //Checks if Any field is indeed null
        if (title.isEmpty() || subtitle.isEmpty() || description.isEmpty()) {
            model.addAttribute("errorMessage", "Todos los campos son obligatorios");
            return "formsMaker";
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
        String formattedDate = now.format(formatter);

        // Fills out the entire Form with the Not null information
        Forms newForm = new Forms(title, subtitle, description, formattedDate, "diablo");
        formsRepository.save(newForm);

        return "redirect:/forms";
    }


}
