package com.ioliveira.ecommerce.controllers;

import com.ioliveira.ecommerce.config.security.authentication.JWTUtil;
import com.ioliveira.ecommerce.config.security.authentication.UserSS;
import com.ioliveira.ecommerce.controllers.dto.request.EmailDTO;
import com.ioliveira.ecommerce.services.AuthService;
import com.ioliveira.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthService authService;

    @PostMapping(path = "/refresh_token")
    public ResponseEntity<String> refreshToken(HttpServletResponse response) {
        final UserSS userAuthenticated = UserService.userAuthenticated();
        final String token = jwtUtil.generateToken(userAuthenticated.getUsername());
        if (token != null){
            response.addHeader("Authorization", "Bearer " + token);
            response.addHeader("access-control-expose-headers", "Authorization");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Nenhum usuário logado!");
    }

    @PostMapping(path = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }

}
