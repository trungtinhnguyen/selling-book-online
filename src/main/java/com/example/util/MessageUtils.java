package com.example.util;

import com.example.constant.Message;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtils {

    public static final String MESSAGE = "message";
    public static final String TYPE = "type";

    public Map<String, String> loadMessage (String key) {
        Map<String, String> result = new HashMap<>();
        key = key.toUpperCase();
        if (key.equals("ACCESS_DENIED")) {
           result.put(MESSAGE, Message.ACCESS_DENIED);
           result.put(TYPE, "danger");
        } else if (key.equals("INCORRECT_LOGIN")) {
            result.put(MESSAGE, Message.INCORRECT_LOGIN);
            result.put(TYPE, "danger");
        } else if (key.equals("ERR_USERNAME_EXISTED")) {
            result.put(MESSAGE, Message.ERR_USERNAME_EXISTED);
            result.put(TYPE, "error");
        } else if (key.equals("ERR_EMAIL_EXISTED")) {
            result.put(MESSAGE, Message.ERR_EMAIL_EXISTED);
            result.put(TYPE, "error");
        } else if (key.equals("ERR_TELL_EXISTED")) {
            result.put(MESSAGE, Message.ERR_TELL_EXISTED);
            result.put(TYPE, "error");
        } else if (key.equals("REGISTERED_SUCCESS")) {
            result.put(MESSAGE, Message.REGISTERED_SUCCESS);
            result.put(TYPE, "success");
        } else if (key.equals("UPDATED_SUCCESS")) {
            result.put(MESSAGE, Message.UPDATED_SUCCESS);
            result.put(TYPE, "success");
        }
        return result;
    }
}
