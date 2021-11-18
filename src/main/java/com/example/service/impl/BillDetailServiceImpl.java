package com.example.service.impl;

import com.example.constant.MessageKey;
import com.example.converter.BillDetailConverter;
import com.example.dto.BillDetailDto;
import com.example.entity.BillDetailEntity;
import com.example.repository.BillDetailRepository;
import com.example.repository.BillRepository;
import com.example.repository.CartRepository;
import com.example.service.BillDetailService;
import com.example.util.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;
    private final BillDetailConverter billDetailConverter;
    private final BillRepository billRepository;
    private final CartRepository cartRepository;
    private final MessageUtils messageUtils;

    public BillDetailServiceImpl(BillDetailRepository billDetailRepository, BillDetailConverter billDetailConverter, BillRepository billRepository, CartRepository cartRepository, MessageUtils messageUtils) {
        this.billDetailRepository = billDetailRepository;
        this.billDetailConverter = billDetailConverter;
        this.billRepository = billRepository;
        this.cartRepository = cartRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public List<BillDetailDto> findById(long[] ids) {
        List<BillDetailDto> items = new ArrayList<>();
        for (Long id : ids) {
            items.add(findOne(id));
        }
        return items;
    }

    @Override
    public BillDetailDto findOne(Long id) {
        return billDetailConverter.toDto(billDetailRepository.findOne(id));
    }

    @Override
    @Transactional
    public BillDetailDto save(BillDetailDto dto) {
        int i = 0;
        BillDetailDto result;
        BillDetailEntity entity;
        Map<String, String> message = new HashMap<>();
        if (dto.getId() == null) {
            List<BillDetailEntity> allItems = billDetailRepository.findByCart(cartRepository.findOne(dto.getCartId()));
            while (i < allItems.size() && !allItems.get(i).getBookIsBought().getId().equals(dto.getBook().getId())) {
                i++;
            }
            if (i == allItems.size()) {
                entity = billDetailConverter.toEntity(dto);
            } else {
                entity = billDetailConverter.toEntity(allItems.get(i), dto);
            }
            message =  messageUtils.loadMessage(MessageKey.ADD_ITEM_SUCCESS);
        } else {
            BillDetailEntity old = billDetailRepository.findOne(dto.getId());
            entity = billDetailConverter.toEntity(old, dto);
        }
        result = billDetailConverter.toDto(billDetailRepository.save(entity));
        result.setResult(message, result.getId() != null);
        result.setSuccess(true);
        return result;
    }

    @Override
    public List<BillDetailDto> findByBillId(long billId) {
        List<BillDetailDto> dtos;
        List<BillDetailEntity> entities = billDetailRepository.findByBill(billRepository.findOne(billId));
        dtos = billDetailConverter.toDtos(entities);
        return dtos;
    }
}
