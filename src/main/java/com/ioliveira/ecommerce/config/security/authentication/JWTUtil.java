package com.ioliveira.ecommerce.config.security.authentication;

import io.jsonwebtoken.Claims;
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

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            final String username = claims.getSubject();
            final Date expiration = claims.getExpiration();
            return username != null && expiration != null && expiration.after(new Date());
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
