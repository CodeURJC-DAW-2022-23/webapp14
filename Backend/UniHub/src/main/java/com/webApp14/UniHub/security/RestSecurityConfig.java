package com.webApp14.UniHub.security;
import com.webApp14.UniHub.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    //Expose AuthenticationManager as a Bean to be used in other services
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/**");

        // URLs that need authentication to access to it
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/forms/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/forms/**").hasAnyRole("ADMIN", "USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/forms/**").hasRole("ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/posts/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/posts/**").hasAnyRole("ADMIN", "USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/posts/**").hasRole("ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/packs/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/packs/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/packs/**").hasRole("ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/auth/logout").hasAnyRole("USER", "ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/all").hasRole("ADMIN");

        // Other URLs can be accessed without authentication
        http.authorizeRequests().anyRequest().permitAll();

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();

        // Disable Http Basic Authentication
        http.httpBasic().disable();

        // Disable Form login Authentication
        http.formLogin().disable();

        // Avoid creating session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add JWT Token filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
