package com.example.converter;

import com.example.constant.SystemConstant;
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
        dto.setStatus(entity.getStatus());
        dto.setUsername(entity.getUser().getUsername());
        dto.setModifiedDate(new Date(entity.getModifiedDate()));
        dto.setDetails(billDetailConverter.toDtos(entity.getDetails()));
        return dto;
    }
    public BillEntity toEntity (BillEntity entity, BillDto dto) {
        entity.setStatus(dto.getStatus());
        return entity;
    }
    public BillEntity toEntity (BillDto dto) {
        BillEntity entity = new BillEntity();
        List<BillDetailEntity> details = new ArrayList<>();
        dto.getDetails().forEach(detail -> details.add(billDetailRepository.findOne(detail.getId())));
        entity.setDetails(details);
        entity.setUser(userRepository.findOneByUsernameAndStatus(dto.getUsername(), SystemConstant.ACTIVE));
        entity.setModifiedDate(dto.getModifiedDate().getTime());
        return toEntity(entity, dto);
    }
}
