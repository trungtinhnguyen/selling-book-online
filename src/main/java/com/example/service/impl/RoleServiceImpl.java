package com.example.service.impl;

import com.example.converter.RoleConverter;
import com.example.dto.RoleDto;
import com.example.entity.RoleEntity;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }


    @Override
    public List<RoleDto> findAll() {
        List<RoleEntity> entities = roleRepository.findAll();
        List<RoleDto> dtos = new ArrayList<>();
        entities.forEach(entity -> {
            dtos.add(roleConverter.toDto(entity));
        });
        return dtos;
    }
}
