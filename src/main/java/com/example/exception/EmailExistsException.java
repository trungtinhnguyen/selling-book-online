package com.example.exception;

import lombok.Getter;

@Getter
public class EmailExistsException extends RuntimeException{

    public EmailExistsException(String message) {
        super(message);
    }
}
