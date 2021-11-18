package com.example.controller.web;

import com.example.constant.SystemConstant;
import com.example.dto.BillDetailDto;
import com.example.dto.BillDto;
import com.example.dto.UserDto;
import com.example.service.BillDetailService;
import com.example.service.BillService;
import com.example.service.CartService;
import com.example.service.UserService;
import com.example.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller (value = "paymentController")
public class PaymentController {

    private final CartService cartService;
    private final BillDetailService billDetailService;
    private final UserService userService;
    private final BillService billService;

    public PaymentController(CartService cartService, BillDetailService billDetailService, UserService userService, BillService billService) {
        this.cartService = cartService;
        this.billDetailService = billDetailService;
        this.userService = userService;
        this.billService = billService;
    }

    @GetMapping (value = "/gio-hang")
    public ModelAndView showCart () {
        String username = SecurityUtils.getPrincipal().getUsername();
        ModelAndView view = new ModelAndView("web/cart-items");
        List<BillDetailDto> model = cartService.findItems(username);
        view.addObject(SystemConstant.MODEL, model);
        return view;
    }

    @GetMapping (value = "/dat-hang")
    public ModelAndView orderPage (@RequestParam (name = "items-id") long items[]) {
        ModelAndView view = new ModelAndView("web/order");
        String username = SecurityUtils.getPrincipal().getUsername();
        UserDto user = userService.findByUserName(username);
        List<BillDetailDto> model = billDetailService.findById(items);
        view.addObject("user", user);
        view.addObject(SystemConstant.MODEL, model);
        return view;
    }

    @GetMapping (value = "/thong-tin-don-hang")
    public ModelAndView orderInfo (@RequestParam (name = "id") long id) {
        ModelAndView view = new ModelAndView();
        String username = SecurityUtils.getPrincipal().getUsername();
        BillDto bill = billService.findOne(id);
        List<BillDetailDto> items;
        if (bill.getId() != null && username != null) {
            view.setViewName("web/order-info");
            items = billDetailService.findByBillId(bill.getId());
            UserDto user = userService.findByUserName(username);
            view.addObject("user", user);
            view.addObject(SystemConstant.MODEL, items);
        } else {
            view.setViewName("redirect:/error/404");
        }
        return view;
    }
}
