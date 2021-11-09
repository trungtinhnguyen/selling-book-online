package com.example.api.admin;

import com.example.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController (value = "adminUserApi")
public class UserApi {

    private final UserService userService;

    public UserApi (UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping (value = "/api/user")
    public void removeUser (@RequestBody long id) {
        userService.disable(id);
    }
}
