package com.example.exception;

import lombok.Getter;

@Getter
public class UsernameExistsException extends RuntimeException{

    public UsernameExistsException(String message) {
        super(message);
    }
}
