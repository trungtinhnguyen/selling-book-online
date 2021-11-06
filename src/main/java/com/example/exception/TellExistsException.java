package com.example.exception;

import lombok.Getter;

@Getter
public class TellExistsException extends RuntimeException{

    public TellExistsException(String message) {
        super(message);
    }
}
