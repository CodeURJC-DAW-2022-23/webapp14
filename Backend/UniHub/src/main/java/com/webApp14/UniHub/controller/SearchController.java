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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

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

    @PostMapping("/search")
    public String search(Model model, @RequestParam("query") String keyword){
        List<Pack> packs = packRepository.findBypackDescriptionLongContaining(keyword);
        model.addAttribute("packs", packs);
        return "customSearch";
    }
}
