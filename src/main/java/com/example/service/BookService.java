package com.example.service;

import com.example.dto.BookDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookDto findOne (Long id);
    BookDto save(BookDto dto);
    void delete(long id);
    List<BookDto> findAll();
    List<BookDto> findAll(Pageable pageable);
    List<BookDto> search (Pageable pageable, String searchText);
    BookDto findBestSeller ();
}
