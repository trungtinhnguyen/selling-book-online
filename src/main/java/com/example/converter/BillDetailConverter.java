package com.example.converter;

import com.example.dto.BillDetailDto;
import com.example.entity.BillDetailEntity;
import com.example.entity.BookEntity;
import com.example.repository.BillRepository;
import com.example.repository.BookRepository;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillDetailConverter {

    private final BillRepository billRepository;
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;
    private final BookConverter bookConverter;

    @Autowired
    public BillDetailConverter(BillRepository billRepository, BookRepository bookRepository, CartRepository cartRepository, BookConverter bookConverter) {
        this.billRepository = billRepository;
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
        this.bookConverter = bookConverter;
    }

    public BillDetailDto toDto (BillDetailEntity entity) {
        if (entity == null) {
            entity = new BillDetailEntity();
        }
        BillDetailDto dto = new BillDetailDto();
        dto.setId(entity.getId());
        dto.setBillId(entity.getBill() == null ? null : entity.getBill().getId());
        dto.setCartId(entity.getCart()==null?null:entity.getCart().getId());
        dto.setBook(bookConverter.toDto(entity.getBookIsBought()));
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
    public BillDetailEntity toEntity (BillDetailEntity entity, BillDetailDto dto) {
        BookEntity book = bookRepository.findOne(dto.getBook().getId());
        entity.setBill(dto.getBillId() == null ? null : billRepository.findOne(dto.getBillId()));
        entity.setBookIsBought(book);
        entity.setCart(dto.getCartId() == null ? null : cartRepository.findOne(dto.getCartId()));
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(book.getPrice());
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
