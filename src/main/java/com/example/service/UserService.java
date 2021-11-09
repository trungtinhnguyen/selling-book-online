package com.example.service;

import com.example.dto.UserDto;

public interface UserService {
    UserDto findOne (Long id);
    UserDto save(UserDto dto);
    void disable(long id);
}
