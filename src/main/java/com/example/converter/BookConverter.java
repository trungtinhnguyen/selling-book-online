package com.example.converter;

import com.example.dto.BookDto;
import com.example.dto.RoleDto;
import com.example.entity.BookEntity;
import com.example.entity.RoleEntity;
import com.example.repository.CategoryRepository;
import com.example.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BookConverter {

    private final CategoryRepository categoryRepository;
    private PublisherRepository publisherRepository;

    @Autowired
    public BookConverter(CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }



    public BookDto toDto (BookEntity entity) {
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCover(entity.getCover());
        dto.setThumbnail(entity.getThumbnail());
        dto.setPageNumber(entity.getPageNumber());
        dto.setPrice(entity.getPrice());
        dto.setCategoryCode(entity.getCategory().getCode());
        dto.setPublisherCode(entity.getPublisher().getCode());
        dto.setPublishedYear(entity.getPublishedYear());
        dto.setImputedDate(new Date(entity.getImputedDate()));
        return dto;
    }
    public BookEntity toEntity (BookEntity entity, BookDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCover(dto.getCover());
        entity.setThumbnail(dto.getThumbnail());
        entity.setPageNumber(dto.getPageNumber());
        entity.setPrice(entity.getPrice());
        entity.setCategory(categoryRepository.findOneByCode (dto.getCategoryCode()));
        entity.setPublisher(publisherRepository.findOneByCode (dto.getPublisherCode()));
        entity.setPublishedYear(dto.getPublishedYear());
        entity.setImputedDate(dto.getImputedDate().getTime());
        return entity;
    }
    public BookEntity toEntity (BookDto dto) {
        return toEntity(new BookEntity(), dto);
    }
}
