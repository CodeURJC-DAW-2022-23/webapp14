package com.webApp14.UniHub.controller;
import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Post;
import com.webApp14.UniHub.model.ThreadPics;
import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.FormsRepository;
import com.webApp14.UniHub.repository.PostRepository;
import com.webApp14.UniHub.repository.ThreadPicsRepository;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class FormsController {

    // Attributes
    @Autowired
    private FormsRepository formsRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadPicsRepository threadPicsRepository;

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

    // Prints the forms.html page with a list of all the current threads
    @GetMapping("/forms")
    public String forms(Model model){
        List<Forms> threadList = formsRepository.findAll();
        model.addAttribute("threadList", threadList);
        return "forms";
    }

    // Loads the post.html with the information of the selected thread given with the id tag
    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") Long id, Model model) {
        Forms forms = formsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thread id"));
        model.addAttribute("post", forms);
        model.addAttribute("id", id);
        return "post";
    }

    /*Creates a comment loading the information of the content and the current date to set it on the post Attributes
    then it returns the current post.html with the comment section updated
     */
    @PostMapping("/post/{id}")
    public String makeComment(@PathVariable("id") Long id, @RequestParam("comment") String comment, Model model) {
        Forms forms = formsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thread id"));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
        String formattedDate = now.format(formatter);

        Post post = new Post(comment, formattedDate, principalUser.getName());

        postRepository.save(post);
        forms.getPosts().add(post);
        formsRepository.save(forms);
        return showPost(id, model);
    }

    // Updates the thread's main upvote counter and adds 1 when the button is pressed
    @PostMapping("/post/{id}/upvote")
    public String threadUpvote(@PathVariable("id") Long id, @RequestParam("threadUpvote") int up, Model model) {
        Forms forms = formsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thread id"));
        int totalVotes = up + forms.getThreadUpvotes();
        // Updates thread Values
        forms.setThreadUpvotes(totalVotes);
        // Saves the thread updates
        formsRepository.save(forms);

        return showPost(id, model);
    }

    // Updates the individual posted comment upvote counter and adds 1 when the button is pressed
    @PostMapping("/post/{id}/upvote/post")
    public String postUpvote(@PathVariable("id") Long id, @RequestParam("postId") Long postId, @RequestParam("upvotePost") int up, Model model) {
        Forms forms = formsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thread id"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post id"));
        int totalVotes = up + post.getPostUpvotes();
        // Updates post Values
        post.setPostUpvotes(totalVotes);
        // Saves the posts updates
        formsRepository.save(forms);

        return showPost(id, model);
    }

    // Loads the formsMaker html and on it, it loads the thread eligible pictures to have them avaiable on the web
    @GetMapping("/forms/formsMaker")
    public String formsMaker(Model model){
        List<ThreadPics> threadPicsList = threadPicsRepository.findAll();
        model.addAttribute("threadPicsList", threadPicsList );
        return "formsMaker";
    }

    // With the information retrieved for the content of a thread, a new thread is created and added to the DB
    @PostMapping("/forms/formsMaker")
    public String handleFormSubmission(@RequestParam("title") String title,
                                       @RequestParam("subtitle") String subtitle,
                                       @RequestParam("description") String description,
                                       @RequestParam("selectedImage") String selectedImage,
                                       Model model) {

        //Checks if Any field is indeed null
        if (title.isEmpty() || subtitle.isEmpty() || description.isEmpty() || selectedImage.isEmpty()) {
            return "formsMaker";
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
        String formattedDate = now.format(formatter);
        String pattern = "http://localhost:8090";
        selectedImage = selectedImage.replaceAll(pattern,"");
        // Fills out the entire Form with the Not null information
        Forms newForm = new Forms(title, subtitle, description, formattedDate, principalUser.getName(), 0, selectedImage);
        formsRepository.save(newForm);

        return forms(model);
    }


}
