package com.example.service.impl;

import com.example.converter.CategoryConverter;
import com.example.dto.CategoryDto;
import com.example.entity.CategoryEntity;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public List<CategoryDto> findAll() {
       List<CategoryDto> dtos = new ArrayList<>();
       List<CategoryEntity> entities = categoryRepository.findAll();
       entities.forEach(entity -> {
           dtos.add(categoryConverter.toDto(entity));
       });
       return dtos;
    }

    @Override
    public CategoryDto findByCode(String code) {
        CategoryEntity entity = categoryRepository.findOneByCode(code);
        return categoryConverter.toDto(entity);
    }
}
