package com.webApp14.UniHub.restControllers;


import com.webApp14.UniHub.security.jwt.AuthResponse;
import com.webApp14.UniHub.security.jwt.LoginRequest;
import com.webApp14.UniHub.security.jwt.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class RestLoginController {

    @Autowired
    private UserLoginService userLoginService;

    // This method is called when the user wants to login

    @Operation(summary = "Login")
    @ApiResponse(
            responseCode = "200",
            description = "Login successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
    )
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> processForm(
            @CookieValue(name = "AuthToken", required = false) String accessToken,
            @CookieValue(name = "RefreshToken", required = false) String refreshToken,
            @RequestBody LoginRequest loginRequest){

        return userLoginService.login(loginRequest, accessToken, refreshToken);

    }

    // This method is called when the user clicks on the "Refresh Token" button
    @Operation(summary = "Refresh Token")
    @ApiResponse(
            responseCode = "200",
            description = "Token refreshed",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
    )
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @CookieValue(name = "RefreshToken", required = false) String refreshToken) {
        return userLoginService.refresh(refreshToken);
    }

    @Operation(summary = "Logout")
    @ApiResponse(
            responseCode = "200",
            description = "Logout successful",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
    )
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @GetMapping("/logout")
    public ResponseEntity<AuthResponse> LogOut(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(new AuthResponse(AuthResponse.Status.SUCCESS, userLoginService.logout(request, response)));
    }

}
