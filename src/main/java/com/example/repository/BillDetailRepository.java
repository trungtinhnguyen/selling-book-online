package com.example.repository;

import com.example.entity.BillDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetailEntity, Long> {
}
