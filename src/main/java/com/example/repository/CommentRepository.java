package com.example.repository;

import com.example.entity.BookEntity;
import com.example.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByCommentOn (BookEntity book);
}
