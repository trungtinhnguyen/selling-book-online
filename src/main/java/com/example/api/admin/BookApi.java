package com.example.api.admin;

import com.example.dto.BookDto;
import com.example.service.BookService;
import com.example.util.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@RestController (value = "adminBookApi")
public class BookApi {

    private final BookService bookService;

    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping (value = "/api/book")
    public BookDto addBook (@RequestBody BookDto dto, HttpSession session) {
        return bookService.save(dto);
    }

    public static void main(String[] args) {

    }
}
