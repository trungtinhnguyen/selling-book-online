package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
    private Long id;

    private boolean success;
    private String message;
    private String type;
}
