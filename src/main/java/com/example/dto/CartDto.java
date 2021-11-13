package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto extends BaseDto{
    private Long id;
    private String username;
    private List<BillDetailDto> items;
}
