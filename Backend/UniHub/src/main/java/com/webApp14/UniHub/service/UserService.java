package com.webApp14.UniHub.service;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElse(null);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}

