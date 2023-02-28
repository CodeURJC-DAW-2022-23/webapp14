package com.webApp14.UniHub.controller;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        if (user.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        User createdUser = userService.save(user);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clientArea/{id}")
    public String clientArea(@PathVariable("id") Long id, Model model){
        User users = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        model.addAttribute("user", users);
        return "clientArea";
    }

}
