package com.example.dto;

import com.example.util.MessageUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BaseDto {
    private Long id;

    private boolean success;
    private String message;
    private String type;

    public void setResult (Map<String, String> message, boolean success) {
        this.message = message.get(MessageUtils.MESSAGE);
        this.type = message.get(MessageUtils.TYPE);
        this.success = success;
    }
}
