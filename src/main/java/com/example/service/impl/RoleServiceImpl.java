package com.example.service.impl;

import com.example.repository.UserRepository;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {


    private final UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public String findAll() {
        userRepository.findAll();
        return null;
    }
}
