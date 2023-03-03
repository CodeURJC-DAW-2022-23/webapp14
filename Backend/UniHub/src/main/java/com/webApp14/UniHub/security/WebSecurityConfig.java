package com.webApp14.UniHub.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/search").permitAll();
        http.authorizeRequests().antMatchers("/packs").permitAll();
        http.authorizeRequests().antMatchers("/packs/{id}").permitAll();
        http.authorizeRequests().antMatchers("/forms").permitAll();
        http.authorizeRequests().antMatchers("/post/{id}").permitAll();

        http.authorizeRequests().antMatchers("/LogIn").permitAll();
        http.authorizeRequests().antMatchers("/LogInError").permitAll();

        http.authorizeRequests().antMatchers("/SignUp").permitAll();
        http.authorizeRequests().antMatchers("/mailserver/connect").permitAll();

        http.authorizeRequests().antMatchers("/clientArea").permitAll();
        http.authorizeRequests().antMatchers("/users/{id}/image").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/LogOut").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/forms/formsMaker").hasAnyRole("USER", "ADMIN");

        // Login form
        http.formLogin().loginPage("/LogIn");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/LogInError");

        // LogOut
        http.logout().logoutSuccessUrl("/");

    }
}