package com.example.service;

import com.example.dto.BillDetailDto;

import java.util.List;

public interface BillDetailService {
    List<BillDetailDto> findById (long[] ids);
    BillDetailDto findOne (Long id);
    BillDetailDto save(BillDetailDto dto);
    List<BillDetailDto> findByBillId(long billId);
}
