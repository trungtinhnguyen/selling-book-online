package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDetailDto extends BaseDto{
    private Long billId;
    private Long bookId;
    private int quantity;
    private double price;
}
