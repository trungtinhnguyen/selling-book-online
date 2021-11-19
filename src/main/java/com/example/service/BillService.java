package com.example.service;

import com.example.dto.BillDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BillService {
    BillDto save (BillDto dto);
    BillDto findOne (long id);
    List<BillDto> findAll (Sort sort);
}
