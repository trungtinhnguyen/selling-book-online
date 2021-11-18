package com.example.api.web;

import com.example.dto.BillDetailDto;
import com.example.dto.BillDto;
import com.example.dto.CartDto;
import com.example.service.BillDetailService;
import com.example.service.BillService;
import com.example.service.BookService;
import com.example.service.CartService;
import com.example.util.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController (value = "billDetailEntity")
public class BillDetailApi {

    private final CartService cartService;
    private final BillDetailService billDetailService;
    private final BookService bookService;
    private final BillService billService;

    public BillDetailApi(CartService cartService, BillDetailService billDetailService, BookService bookService, BillService billService) {
        this.cartService = cartService;
        this.billDetailService = billDetailService;
        this.bookService = bookService;
        this.billService = billService;
    }

    @PostMapping(value = "/api/cart")
    public BillDetailDto addItemToCart (@RequestBody long id) {
        String username = Objects.requireNonNull(SecurityUtils.getPrincipal()).getUsername();
        CartDto cart = cartService.findOneByUsername(username);
        BillDetailDto item = new BillDetailDto();
        item.setCartId(cart.getId());
        item.setBook(bookService.findOne(id));
        item.setQuantity(1);
        return billDetailService.save(item);
    }

    @PutMapping (value = "/api/cart")
    public BillDetailDto upItemQuantity (@RequestBody long id, @RequestParam (name = "type") String type) {
        String username = SecurityUtils.getPrincipal().getUsername();
        BillDetailDto item = billDetailService.findOne(id);
        if (type.equals("up")) {
            item.setQuantity(1);
        } else {
            item.setQuantity(-1);
        }
        return billDetailService.save(item);
    }

    @PostMapping (value = "/api/payment")
    public BillDto pay (@RequestBody BillDto dto) {
        String username = SecurityUtils.getPrincipal().getUsername();
        BillDto bill = new BillDto();
        bill.setUsername(username);
        bill.setModifiedDate(new Date(System.currentTimeMillis()));
        List<BillDetailDto> listItems = billDetailService.findById(dto.getItemIds());
        bill.setDetails(listItems);
        bill = billService.save(bill);
        return bill;
    }
}
