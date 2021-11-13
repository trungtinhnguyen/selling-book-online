package com.example.service;

import com.example.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findByCode (String code);
}
