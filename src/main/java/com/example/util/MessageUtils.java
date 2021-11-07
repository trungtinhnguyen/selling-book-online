package com.example.util;

import com.example.constant.Message;
import com.example.constant.MessageKey;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtils {

    public static final String MESSAGE = "message";
    public static final String TYPE = "type";

    public  Map<String, String> loadMessage (String key) {
        Map<String, String> result = new HashMap<>();
        key = key.toUpperCase();
        if (key.equals(MessageKey.ACCESS_DENIED)) {
           result.put(MESSAGE, Message.ACCESS_DENIED);
           result.put(TYPE, "danger");
        } else if (key.equals(MessageKey.INCORRECT_LOGIN)) {
            result.put(MESSAGE, Message.INCORRECT_LOGIN);
            result.put(TYPE, "danger");
        } else if (key.equals(MessageKey.USERNAME_EXISTED)) {
            result.put(MESSAGE, Message.ERR_USERNAME_EXISTED);
            result.put(TYPE, "error");
        } else if (key.equals(MessageKey.EMAIL_EXISTED)) {
            result.put(MESSAGE, Message.ERR_EMAIL_EXISTED);
            result.put(TYPE, "error");
        } else if (key.equals(MessageKey.TELL_EXISTED)) {
            result.put(MESSAGE, Message.ERR_TELL_EXISTED);
            result.put(TYPE, "error");
        } else if (key.equals(MessageKey.REGISTERED_SUCCESS)) {
            result.put(MESSAGE, Message.REGISTERED_SUCCESS);
            result.put(TYPE, "success");
        } else if (key.equals(MessageKey.USERNAME_EXISTED)) {
            result.put(MESSAGE, Message.UPDATED_SUCCESS);
            result.put(TYPE, "success");
        }else if (key.equals("USER_NOT_FOUND")) {
            result.put(MESSAGE, Message.USER_NOT_FOUND);
            result.put(TYPE, "error");
        }
        return result;
    }
}
