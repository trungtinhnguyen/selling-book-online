package com.example.service.impl;

import com.example.constant.SystemConstant;
import com.example.converter.BillDetailConverter;
import com.example.converter.CartConverter;
import com.example.dto.BillDetailDto;
import com.example.dto.CartDto;
import com.example.entity.CartEntity;
import com.example.repository.CartRepository;
import com.example.repository.UserRepository;
import com.example.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartConverter cartConverter;
    private final UserRepository userRepository;
    private final BillDetailConverter billDetailConverter;

    public CartServiceImpl(CartRepository cartRepository, CartConverter cartConverter, UserRepository userRepository, BillDetailConverter billDetailConverter) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
        this.userRepository = userRepository;
        this.billDetailConverter = billDetailConverter;
    }

    @Override
    public CartDto findOne(long id) {
        CartEntity entity = cartRepository.findOne(id);
        return cartConverter.toDto(entity);
    }

    @Override
    public CartDto findOneByUsername(String username) {
       CartEntity entity = cartRepository.findOneByOwner(userRepository.findOneByUsernameAndStatus(username, SystemConstant.ACTIVE));
       return cartConverter.toDto(entity);
    }

    @Override
    @Transactional
    public CartDto save(CartDto dto) {
        CartEntity entity;
        if (dto.getId() == null) {
            entity = cartConverter.toEntity(dto);
        } else {
            CartEntity old = cartRepository.findOne(dto.getId());
            entity = cartConverter.toEntity(old, dto);
        }
        return cartConverter.toDto(cartRepository.save(entity));
    }

    @Override
    public CartDto addItem(Long itemId) {

        return null;
    }

    @Override
    public List<BillDetailDto> findItems(String username) {
        CartEntity cart = cartRepository.findOneByOwner(userRepository.findOneByUsernameAndStatus(username, SystemConstant.ACTIVE));
        return billDetailConverter.toDtos(cart.getItems());
    }
}
