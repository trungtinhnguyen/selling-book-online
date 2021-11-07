package com.example.converter;

import com.example.dto.RoleDto;
import com.example.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDto toDto (RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        return dto;
    }
    public RoleEntity toEntity (RoleEntity entity, RoleDto dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }
    public RoleEntity toEntity (RoleDto dto) {
        return toEntity(new RoleEntity(), dto);
    }
}
