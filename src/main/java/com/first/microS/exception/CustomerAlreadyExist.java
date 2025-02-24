package com.first.microS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExist extends RuntimeException{
    public CustomerAlreadyExist(String message) {
        super(message);
    }
}
