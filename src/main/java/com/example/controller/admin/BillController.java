package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.BillDto;
import com.example.service.BillService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller (value = "adminBillController")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping (value = "/quan-tri/hoa-don/danh-sach")
    public ModelAndView showListBill() {
        ModelAndView view = new ModelAndView("admin/bill/list");
        Sort sort = new Sort(Sort.Direction.DESC, "modifiedDate");
        List<BillDto> model = billService.findAll(sort);
        view.addObject(SystemConstant.MODEL, model);
        return view;
    }
}
