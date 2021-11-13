package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.BillDetailDto;
import com.example.dto.CartDto;
import com.example.service.BillDetailService;
import com.example.service.CartService;
import com.example.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller (value = "paymentController")
public class PaymentController {

    private final CartService cartService;
    private final BillDetailService billDetailService;

    public PaymentController(CartService cartService, BillDetailService billDetailService) {
        this.cartService = cartService;
        this.billDetailService = billDetailService;
    }

    @GetMapping (value = "/gio-hang")
    public ModelAndView showCart () {
        String username = SecurityUtils.getPrincipal().getUsername();
        ModelAndView view = new ModelAndView("web/cart-items");
        List<BillDetailDto> model = cartService.findItems(username);
        view.addObject(SystemConstant.MODEL, model);
        return view;
    }
}
