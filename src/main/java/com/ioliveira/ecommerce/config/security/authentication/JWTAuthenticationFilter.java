package com.ioliveira.ecommerce.config.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioliveira.ecommerce.controllers.dto.request.CredenciaisDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        //Set da implementacao maunal em caso de falha na autenticacao
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        setFilterProcessesUrl("/auth");//Path customizado de login
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            CredenciaisDTO credenciaisDTO = new ObjectMapper()
                    .readValue(request.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    credenciaisDTO.getEmail(), credenciaisDTO.getSenha(), new ArrayList<>());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
    }

    public JWTAuthenticationFilter() {
        super();
    }
}
