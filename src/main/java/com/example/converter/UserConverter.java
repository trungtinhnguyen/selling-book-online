package com.example.converter;

import com.example.dto.UserDto;
import com.example.entity.CartEntity;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private RoleRepository roleRepository;

    public UserDto toDto (UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());
        dto.setTell(entity.getTell());
        List<String> roles = new ArrayList<>();
        entity.getRoles().forEach(e -> roles.add(e.getCode()));
        dto.setRoles(roles);
        dto.setCartId(entity.getCart().getId());
        return dto;
    }
    public UserEntity toEntity (UserEntity entity, UserDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setTell(dto.getTell());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus());
        List<RoleEntity> roles = new ArrayList<>();
        dto.getRoles().forEach (role -> {
            roles.add(roleRepository.findOneByCode(role));
        });
        entity.setRoles(roles);
        return entity;
    }
    public UserEntity toEntity (UserDto dto) {
        UserEntity entity = new UserEntity();
        CartEntity cart = new CartEntity();
        entity = toEntity(entity, dto);
        cart.setOwner(entity);
        entity.setCart(cart);
        return entity;
    }
}
