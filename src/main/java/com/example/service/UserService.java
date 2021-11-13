package com.example.service;

import com.example.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findOne (Long id);
    UserDto save(UserDto dto);
    UserDto findByUserName (String username);
    void disable(long id);
}
