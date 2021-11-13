package com.example.api.web;

import com.example.dto.UserDto;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController (value = "webUserApi")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/user")
    public UserDto createAccount (@RequestBody UserDto dto) {
        return userService.save(dto);
    }

    @PutMapping (value = "/api/user")
    public UserDto updateUserInfo (@RequestBody UserDto dto) {
        return userService.save(dto);
    }
}
