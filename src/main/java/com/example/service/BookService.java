package com.example.service;

import com.example.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto findOne (Long id);
    BookDto save(BookDto dto);
    List<BookDto> findAll();
    void delete(long id);
}
