package com.ioliveira.ecommerce.services.exceptions;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String msg) {
        super(msg);
    }

}
