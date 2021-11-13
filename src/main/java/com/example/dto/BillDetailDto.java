package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDetailDto extends BaseDto{
    private Long billId;
    private BookDto book;
    private Long cartId;
    private int quantity;

    public Double getPrices () {
        return book.getPrice() * quantity;
    }
}
