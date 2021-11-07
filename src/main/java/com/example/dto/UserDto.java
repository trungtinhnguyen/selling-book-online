package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto{
    private String username;
    private String fullName;
    private String password;
    private String email;
    private String address;
    private String tell;
    private int status;
    private List<String> roles;
}
