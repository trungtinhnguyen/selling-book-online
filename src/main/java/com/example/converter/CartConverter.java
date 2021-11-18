package com.example.converter;

import com.example.constant.SystemConstant;
import com.example.dto.BillDetailDto;
import com.example.dto.CartDto;
import com.example.entity.BillDetailEntity;
import com.example.entity.CartEntity;
import com.example.repository.BillDetailRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartConverter {

    private final BillDetailRepository billDetailRepository;
    private final UserRepository userRepository;
    private final BillDetailConverter billDetailConverter;

    public CartConverter(BillDetailRepository billDetailRepository, UserRepository userRepository, BillDetailConverter billDetailConverter) {
        this.billDetailRepository = billDetailRepository;
        this.userRepository = userRepository;
        this.billDetailConverter = billDetailConverter;
    }

    public CartDto toDto (CartEntity entity) {
        CartDto dto = new CartDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getOwner().getUsername());
        List<BillDetailEntity> items = new ArrayList<>(entity.getItems());
        dto.setItems(billDetailConverter.toDtos(items));
        return dto;
    }

    public CartEntity toEntity (CartEntity entity, CartDto dto) {
       List<BillDetailEntity> items = new ArrayList<>();
        for (BillDetailDto item : dto.getItems()) {
            items.add(billDetailRepository.findOne(item.getId()));
        }
        entity.setItems(items);
        entity.setOwner(userRepository.findOneByUsernameAndStatus(dto.getUsername(), SystemConstant.ACTIVE));
        return entity;
    }
    public CartEntity toEntity (CartDto dto) {
        CartEntity cart = new CartEntity();
        return toEntity(cart, dto);
    }
}
