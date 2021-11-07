package com.example.converter;

import com.example.dto.BillDetailDto;
import com.example.entity.BillDetailEntity;
import com.example.entity.BookEntity;
import com.example.repository.BillRepository;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillDetailConverter {

    private final BillRepository billRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BillDetailConverter(BillRepository billRepository, BookRepository bookRepository) {
        this.billRepository = billRepository;
        this.bookRepository = bookRepository;
    }

    public BillDetailDto toDto (BillDetailEntity entity) {
        BillDetailDto dto = new BillDetailDto();
        dto.setId(entity.getId());
        dto.setBillId(entity.getBill().getId());
        dto.setBookId(entity.getBookIsBought().getId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }
    public BillDetailEntity toEntity (BillDetailEntity entity, BillDetailDto dto) {
        entity.setBill(billRepository.findOne(dto.getBillId()));
        entity.setBookIsBought(bookRepository.findOne(dto.getBookId()));
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        return entity;
    }
    public BillDetailEntity toEntity (BillDetailDto dto) {
        return toEntity(new BillDetailEntity(), dto);
    }

    public List<BillDetailDto> toDtos (List<BillDetailEntity> entities) {
        List<BillDetailDto> dtos = new ArrayList<>();
        entities.forEach(entity -> {
            dtos.add(toDto(entity));
        });
        return dtos;
    }
}
