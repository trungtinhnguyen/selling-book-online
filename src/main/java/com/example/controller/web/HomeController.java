package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.BookDto;
import com.example.dto.CategoryDto;
import com.example.dto.PublisherDto;
import com.example.service.BookService;
import com.example.service.CategoryService;
import com.example.service.PublisherService;
import com.example.util.MessageUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller ("webHomeController")
public class HomeController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    public HomeController(BookService bookService, CategoryService categoryService, PublisherService publisherService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @RequestMapping (value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage () {
        ModelAndView view = new ModelAndView("web/home");
        Sort sort = new Sort(Sort.Direction.DESC, "imputedDate");
        Pageable pageable = new PageRequest(0, 10, sort);
        BookDto bestSeller = bookService.findBestSeller();
        List<BookDto> books = bookService.findAll(pageable);
        if (books.contains(bestSeller)) {
            books.remove(bestSeller);
        }
        view.addObject("bestSeller", bestSeller);
        view.addObject(SystemConstant.MODEL, books);
        return view;
    }

    @GetMapping (value = "/tim-kiem")
    public ModelAndView search (HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("web/search-item");
        String searchText = request.getParameter("search-text");
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        Pageable pageable = new PageRequest(page, size);
        List<BookDto> model = bookService.search(pageable, searchText);
        view.addObject(SystemConstant.MODEL, model);
        Map<String, Integer> param = new HashMap<>();
        param.put("page", page);
        param.put("size", size);
        view.addObject("page", param);
        return view;
    }

    @RequestMapping (value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav =  new ModelAndView("auth/login");
        if (request.getParameter("message") != null) {
            Map<String, String> message = new MessageUtils().loadMessage(request.getParameter("message"));
            mav.addObject("message", message.get(MessageUtils.MESSAGE));
            mav.addObject("alert", message.get(MessageUtils.TYPE));
        }
        return mav;
    }

    @RequestMapping (value = "/dang-xuat", method = RequestMethod.GET)
    public ModelAndView logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/trang-chu");
    }
    @RequestMapping (value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView accessDenied () {
        return new ModelAndView("redirect:/dang-nhap?message=access_denied");
    }

    @GetMapping(value = "/error/404")
    public ModelAndView pageNotFound () {
        return new ModelAndView("error/404");
    }

    @RequestMapping (value = "/dang-ky", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("auth/register");
    }

    @GetMapping (value = "/sach")
    public ModelAndView bookDetailsPage (@RequestParam (name = "id") long id) {
        ModelAndView view = new ModelAndView("web/book-detail");
        BookDto model = bookService.findOne(id);

        Map<String, String> map = new HashMap<>();
        if (model.getId() == null) {
            view.setViewName("redirect:/error/404");
        } else {
            List<BookDto> relatedBook = bookService.findByCategory(model.getCategoryCode());
            CategoryDto category = categoryService.findByCode(model.getCategoryCode());
            PublisherDto publisher = publisherService.findByCode(model.getPublisherCode());
            relatedBook.remove(model);
            map.put(category.getCode(), category.getName());
            map.put(publisher.getCode(), publisher.getName());
            view.addObject("related", relatedBook);
            view.addObject("map", map);
            view.addObject(SystemConstant.MODEL, model);
        }
        return view;
    }
}
