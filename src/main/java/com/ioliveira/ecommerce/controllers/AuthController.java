package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.config.security.authentication.JWTUtil;
import com.ioliveira.ecommerce.config.security.authentication.UserSS;
import com.ioliveira.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/refresh_token")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        final UserSS userAuthenticated = UserService.userAuthenticated();
        final String token = jwtUtil.generateToken(userAuthenticated.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

}
