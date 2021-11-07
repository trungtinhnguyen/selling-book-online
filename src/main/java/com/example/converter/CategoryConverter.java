package com.example.converter;

import com.example.dto.CategoryDto;
import com.example.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryDto toDto (CategoryEntity entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        return dto;
    }
    public CategoryEntity toEntity (CategoryEntity entity, CategoryDto dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }
    public CategoryEntity toEntity (CategoryDto dto) {
        return toEntity(new CategoryEntity(), dto);
    }
}
