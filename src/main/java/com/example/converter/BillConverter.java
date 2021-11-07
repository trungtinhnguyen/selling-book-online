package com.example.converter;

import com.example.dto.BillDto;
import com.example.entity.BillDetailEntity;
import com.example.entity.BillEntity;
import com.example.repository.BillDetailRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BillConverter {

    private final BillDetailConverter billDetailConverter;
    private final UserRepository userRepository;
    private final BillDetailRepository billDetailRepository;

    public BillConverter(BillDetailConverter billDetailConverter, UserRepository userRepository, BillDetailRepository billDetailRepository) {
        this.billDetailConverter = billDetailConverter;
        this.userRepository = userRepository;
        this.billDetailRepository = billDetailRepository;
    }

    public BillDto toDto (BillEntity entity) {
        BillDto dto = new BillDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setModifiedDate(new Date(entity.getModifiedDate()));
        dto.setDetails(billDetailConverter.toDtos(entity.getDetails()));
        return dto;
    }
    public BillEntity toEntity (BillEntity entity, BillDto dto) {
        entity.setUser(userRepository.findOne(dto.getUserId()));
        List<BillDetailEntity> details = new ArrayList<>();
        dto.getDetails().forEach(detail -> {
           details.add(billDetailRepository.findOne(detail.getId()));
        });
        entity.setDetails(details);
        entity.setStatus(dto.getStatus());
        entity.setModifiedDate(dto.getModifiedDate().getTime());
        return entity;
    }
    public BillEntity toEntity (BillDto dto) {
        return toEntity(new BillEntity(), dto);
    }
}
