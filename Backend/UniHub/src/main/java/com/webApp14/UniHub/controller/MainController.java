package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.repository.FormsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    FormsRepository userRepository;
    @Autowired
    FormsRepository formsRepository;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
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
        Principal principal = request.getUserPrincipal();
        String users = principal.getName();
        List<Forms> threadList = formsRepository.findAll();
        model.addAttribute("threads", threadList);
        model.addAttribute("user", users);
        return "clientArea";
    }
}
