package com.webApp14.UniHub.restControllers;


import com.webApp14.UniHub.security.jwt.AuthResponse;
import com.webApp14.UniHub.security.jwt.LoginRequest;
import com.webApp14.UniHub.security.jwt.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class RestLoginController {

    @Autowired
    private UserLoginService userLoginService;

    // This method is called when the user wants to login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> processForm(
            @CookieValue(name = "AuthToken", required = false) String accessToken,
            @CookieValue(name = "RefreshToken", required = false) String refreshToken,
            @RequestBody LoginRequest loginRequest){
        return userLoginService.login(loginRequest, accessToken, refreshToken);

    }

    // This method is called when the user clicks on the "Refresh Token" button
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @CookieValue(name = "RefreshToken", required = false) String refreshToken) {
        return userLoginService.refresh(refreshToken);
    }


    @GetMapping("/logout")
    public ResponseEntity<AuthResponse> LogOut(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(new AuthResponse(AuthResponse.Status.SUCCESS, userLoginService.logout(request, response)));
    }

}
