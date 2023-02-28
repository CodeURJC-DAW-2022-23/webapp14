package com.webApp14.UniHub.controller;
import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Post;
import com.webApp14.UniHub.model.ThreadPics;
import com.webApp14.UniHub.repository.FormsRepository;
import com.webApp14.UniHub.repository.PostRepository;
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

    // Attributes
    @Autowired
    private FormsRepository formsRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ThreadPicsRepository threadPicsRepository;


    // Methods for URL mappings

    @GetMapping("/forms")
    public String forms(Model model){
        List<Forms> threadList = formsRepository.findAll();
        model.addAttribute("threadList", threadList);
        return "forms";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model){
        Forms forms = formsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thread id"));
        model.addAttribute("post", forms);
        return "post";
    }


    @PostMapping("/post/{id}")
    public String makeComment(@PathVariable("id") Long id, @RequestParam("comment") String comment) {
        Forms forms = formsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thread id"));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
        String formattedDate = now.format(formatter);

        Post post = new Post(comment, formattedDate,"Don Diablo");

        postRepository.save(post);
        forms.getPosts().add(post);
        formsRepository.save(forms);
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
                                       @RequestParam("selectedImage") String selectedImage,
                                       Model model,
                                       Authentication authentication) {

        //Checks if Any field is indeed null
        if (title.isEmpty() || subtitle.isEmpty() || description.isEmpty() || selectedImage.isEmpty()) {
            model.addAttribute("errorMessage", "Fields Missing");
            return "formsMaker";
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
        String formattedDate = now.format(formatter);

        // Fills out the entire Form with the Not null information
        Forms newForm = new Forms(title, subtitle, description, formattedDate, "Ruky", 0, selectedImage);
        formsRepository.save(newForm);

        // User user = usuario entero
        // userRepository.save(user);

        return "redirect:/forms";
    }


}
