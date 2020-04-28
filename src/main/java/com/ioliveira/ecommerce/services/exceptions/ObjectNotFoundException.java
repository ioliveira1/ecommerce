package com.ioliveira.ecommerce.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException (String msg){
        super(msg);
    }

}
