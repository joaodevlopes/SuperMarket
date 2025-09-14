package com.supermarket.supermarket.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

public class ResouceNotFoundException extends RuntimeException {
    public ResouceNotFoundException(String message){
        super(message);
    }
}
