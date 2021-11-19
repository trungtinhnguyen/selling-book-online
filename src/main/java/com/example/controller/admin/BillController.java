package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.BillDto;
import com.example.dto.UserDto;
import com.example.service.BillService;
import com.example.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller (value = "adminBillController")
public class BillController {

    private final BillService billService;
    private final UserService userService;

    public BillController(BillService billService, UserService userService) {
        this.billService = billService;
        this.userService = userService;
    }

    @GetMapping (value = "/quan-tri/hoa-don/danh-sach")
    public ModelAndView showListBill() {
        ModelAndView view = new ModelAndView("admin/bill/list");
        Sort sort = new Sort(Sort.Direction.DESC, "modifiedDate");
        List<BillDto> model = billService.findAll(sort);
        view.addObject(SystemConstant.MODEL, model);
        return view;
    }

    @GetMapping (value = "/quan-tri/hoa-don/chi-tiet")
    public ModelAndView billDetail (@RequestParam (name = "id") long id) {
        ModelAndView view = new ModelAndView("admin/bill/detail");
        BillDto model = billService.findOne(id);
        UserDto user = userService.findByUserName(model.getUsername());
        view.addObject(SystemConstant.MODEL, model);
        view.addObject("user", user);
        return view;
    }
}
