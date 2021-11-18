package com.example.service;

import com.example.dto.BillDto;

public interface BillService {
    BillDto save (BillDto dto);
    BillDto findOne (long id);
}
