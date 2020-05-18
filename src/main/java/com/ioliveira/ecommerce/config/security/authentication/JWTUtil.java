package com.ioliveira.ecommerce.config.security.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    //Geracao do token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)//usuario
                .setExpiration(new Date(System.currentTimeMillis() + expiration))//tempo de expiracao
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())//assinatura do token (algoritmo + palavra)
                .compact();
    }
}
