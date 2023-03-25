package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.PackRepository;
import com.webApp14.UniHub.repository.UserRepository;
import com.webApp14.UniHub.security.RepositoryUserDetailsService;
import com.webApp14.UniHub.security.jwt.RegisterRequest;
import com.webApp14.UniHub.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class RestUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PackRepository packRepository;

    private User user;

    @Operation(summary = "Get the current user")
    @ApiResponse(
            responseCode = "200",
            description = "Found the user",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    )
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @GetMapping("/me")
    public ResponseEntity<User> getProfile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByUsername(principal.getName());
        if(userPrincipal.isPresent()) {
            this.user = userPrincipal.get();
            return new ResponseEntity<>(this.user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all users")
    @ApiResponse(
            responseCode = "200",
            description = "Found the users",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    )
    @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @GetMapping("/all")
    public ResponseEntity<Collection<User>> getProfiles(HttpServletRequest request){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Create a new user")
    @ApiResponse(
            responseCode = "201",
            description = "Created the user",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    )
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
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

    // Adds a pack to a user
    @Operation(summary = "Add a pack to a user")
    @ApiResponse(
            responseCode = "200",
            description = "Added the pack to the user",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    )
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @PutMapping("/addPack/{id}")
    public ResponseEntity<User> addPack(@PathVariable long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByUsername(principal.getName());
        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            user.getPackList().add(packRepository.findById(id).get());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Changes the current profile picture
    @Operation(summary = "Change the current profile picture")
    @ApiResponse(
            responseCode = "200",
            description = "Changed the profile picture",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
    )
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @PutMapping("/profilePic/image")
    public ResponseEntity<User> changePic(HttpServletRequest request, @RequestParam("image") MultipartFile image) throws IOException {
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByUsername(principal.getName());
        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            user.setImage(image.getBytes());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
