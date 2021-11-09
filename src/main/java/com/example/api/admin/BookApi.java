package com.example.api.admin;

import com.example.dto.BookDto;
import com.example.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController (value = "adminBookApi")
public class BookApi {

    private final BookService bookService;

    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping (value = "/api/book")
    public BookDto addBook (@RequestBody BookDto dto) {
        return bookService.save(dto);
    }

    @PutMapping (value = "/api/book")
    public BookDto updateBook (@RequestBody BookDto dto) {
       return bookService.save(dto);
    }

    @DeleteMapping (value = "/api/book")
    public void deleteBook (@RequestBody long id) {
        bookService.delete(id);
    }
}
