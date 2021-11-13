package com.example.repository;

import com.example.entity.CartEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findOneByOwner (UserEntity user);
}
