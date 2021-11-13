package com.example.service.impl;

import com.example.constant.MessageKey;
import com.example.converter.BillDetailConverter;
import com.example.dto.BillDetailDto;
import com.example.entity.BillDetailEntity;
import com.example.repository.BillDetailRepository;
import com.example.repository.BookRepository;
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
    private final BookRepository bookRepository;
    private final MessageUtils messageUtils;

    public BillDetailServiceImpl(BillDetailRepository billDetailRepository, BillDetailConverter billDetailConverter, BookRepository bookRepository, MessageUtils messageUtils) {
        this.billDetailRepository = billDetailRepository;
        this.billDetailConverter = billDetailConverter;
        this.bookRepository = bookRepository;
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
        BillDetailDto result;
        BillDetailEntity entity;
        Map<String, String> message = new HashMap<>();
        if (dto.getId() == null) {
            entity = billDetailConverter.toEntity(dto);
            message =  messageUtils.loadMessage(MessageKey.ADD_ITEM_SUCCESS);
        } else {
            BillDetailEntity old = billDetailRepository.findOne(dto.getId());
            if (dto.getBillId() != null) {
                if (old.getCart() != null) {
                    old.setCart(null);
                }
            }
            dto.setQuantity(old.getQuantity() + dto.getQuantity());
            entity = billDetailConverter.toEntity(old, dto);
        }
        result = billDetailConverter.toDto(billDetailRepository.save(entity));
        result.setResult(message, result.getId() != null);
        return result;
    }
}
