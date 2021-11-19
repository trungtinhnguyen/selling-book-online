package com.example.repository;

import com.example.entity.BookEntity;
import com.example.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByNameContains (String searchText);
    List<BookEntity> findByCategory (CategoryEntity category);
}
