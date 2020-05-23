package com.ioliveira.ecommerce.services;

import com.ioliveira.ecommerce.config.security.authentication.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS userAuthenticated() {
        try {
            //Retorna o usuario logado no sistema
            return (UserSS)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            return null;
        }
    }

}
