package com.example.repository;

import com.example.entity.BillDetailEntity;
import com.example.entity.BillEntity;
import com.example.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetailEntity, Long> {
    List<BillDetailEntity> findByBill(BillEntity bill);
    List<BillDetailEntity> findByCart(CartEntity cart);
}
