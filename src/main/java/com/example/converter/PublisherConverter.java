package com.example.converter;

import com.example.dto.PublisherDto;
import com.example.entity.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherConverter {

    public PublisherDto toDto (PublisherEntity entity) {
        PublisherDto dto = new PublisherDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        return dto;
    }
    public PublisherEntity toEntity (PublisherEntity entity, PublisherDto dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }
    public PublisherEntity toEntity (PublisherDto dto) {
        return toEntity(new PublisherEntity(), dto);
    }
}
