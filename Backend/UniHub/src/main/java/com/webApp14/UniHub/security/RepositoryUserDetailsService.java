package com.webApp14.UniHub.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webApp14.UniHub.model.User;
import com.webApp14.UniHub.repository.UserRepository;

@Service
public class RepositoryUserDetailsService implements UserDetailsService {

    // Attributes
    @Autowired
    private UserRepository userRepository;

    // Loads the user found on the database given the username sent to it
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : user.getRoles()) {
           roles.add(new SimpleGrantedAuthority("ROLE_" + role));
            }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), roles);

    }

}

