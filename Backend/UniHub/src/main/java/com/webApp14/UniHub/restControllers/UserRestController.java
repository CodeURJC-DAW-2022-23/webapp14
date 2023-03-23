package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.controller.RegisterController;
import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.UserRepository;
import com.webApp14.UniHub.security.RepositoryUserDetailsService;
import com.webApp14.UniHub.security.jwt.RegisterRequest;
import com.webApp14.UniHub.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    private User user;

    @GetMapping("/me")
    public ResponseEntity<User> getProfile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByUsername(principal.getName());

        if(userPrincipal.isPresent()) {
            this.user = userPrincipal.get();
            long id = this.user.getId();
            String username = this.user.getUsername();
            String email = this.user.getEmail();
            return new ResponseEntity<>(this.user, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<User>> getProfiles(HttpServletRequest request){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> processRegister(@RequestBody RegisterRequest registerRequest){

        String name = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        User user = new User(name, email, passwordEncoder.encode(password), "USER");

        Optional<User> tryUser = userRepository.findByUsername(user.getUsername());
        Optional<User> tryMail = userRepository.findByEmail(user.getEmail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userRepository.save(user);
            String subject = "Bienvenido a nuestra aplicación";
            String content = "Gracias por registrarte en nuestra aplicación. Esperamos que disfrutes usándola.";
            emailService.sendEmail(user.getEmail(), subject, content);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
