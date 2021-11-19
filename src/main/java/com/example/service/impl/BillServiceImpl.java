package com.example.service.impl;

import com.example.constant.SystemConstant;
import com.example.converter.BillConverter;
import com.example.dto.BillDetailDto;
import com.example.dto.BillDto;
import com.example.entity.BillEntity;
import com.example.repository.BillRepository;
import com.example.service.BillDetailService;
import com.example.service.BillService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillConverter billConverter;
    private final BillDetailService billDetailService;

    public BillServiceImpl(BillRepository billRepository, BillConverter billConverter, BillDetailService billDetailService) {
        this.billRepository = billRepository;
        this.billConverter = billConverter;
        this.billDetailService = billDetailService;
    }

    @Override
    @Transactional
    public BillDto save(BillDto dto) {
        BillEntity entity;
        if (dto.getId() == null) {
            dto.setStatus(SystemConstant.ACTIVE);
            entity = billConverter.toEntity(dto);
        } else {
            BillEntity old = billRepository.findOne(dto.getId());
            entity = billConverter.toEntity(old, dto);
        }
        BillDto result = billConverter.toDto(billRepository.save(entity));
        if (result == null) {
            result = new BillDto();
            result.setSuccess(false);
        } else {
            List<BillDetailDto> items = result.getDetails();
            for (BillDetailDto item: items) {
                item.setBillId(result.getId());
                billDetailService.save(item);
            }
            result.setSuccess(true);
        }
        return result;
    }

    @Override
    public BillDto findOne(long id) {
        BillEntity entity = billRepository.findOne(id);
        if (entity == null) {
            return new BillDto();
        } else {
            return billConverter.toDto(entity);
        }
    }

    @Override
    public List<BillDto> findAll(Sort sort) {
        List<BillDto> dtos = new ArrayList<>();
        List<BillEntity> entities = billRepository.findAll(sort);
        entities.forEach(entity -> dtos.add(billConverter.toDto(entity)));
        return dtos;
    }
}
