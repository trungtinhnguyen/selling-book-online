package com.example.service;

import com.example.dto.BillDetailDto;
import com.example.dto.CartDto;
import com.example.entity.CartEntity;

import java.util.List;

public interface CartService {
    CartDto findOne (long id);
    CartDto findOneByUsername (String username);
    CartDto save(CartDto cart);
    CartDto addItem (Long itemId);
    List<BillDetailDto> findItems (String username);
}
