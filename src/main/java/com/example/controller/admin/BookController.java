package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.BookDto;
import com.example.dto.CategoryDto;
import com.example.service.BookService;
import com.example.service.CategoryService;
import com.example.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller (value = "adminBookController")
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, CategoryService categoryService, PublisherService publisherService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @RequestMapping (value = "/quan-tri/sach/chinh-sua", method = RequestMethod.GET)
    public ModelAndView bookEditPage (@RequestParam (name = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView("admin/book/edit");
        BookDto model;
        if (id != null) {
            model = bookService.findOne(id);
        } else {
            model = new BookDto();
        }
        view.addObject("publishers", publisherService.findAll());
        view.addObject("categories", categoryService.findAll());
        view.addObject(SystemConstant.MODEL, model);
        return view;
    }

    @GetMapping (value = "/quan-tri/sach/danh-sach")
    public ModelAndView bookListPage () {
        ModelAndView view = new ModelAndView("admin/book/list");
        List<BookDto> listModel = bookService.findAll();
        List<CategoryDto> categories = categoryService.findAll();
        view.addObject("categories", categories);
        view.addObject(SystemConstant.MODEL, listModel);
        return view;
    }
}
