package com.example.converter;

import com.example.dto.CommentDto;
import com.example.entity.CommentEntity;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public CommentConverter (UserRepository userRepository, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public CommentDto toDto (CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setParentId(entity.getParentID());
        dto.setContent(entity.getContent());
        dto.setCommentBy(entity.getCommentBy().getId());
        dto.setCommentOn(entity.getCommentOn().getId());
        return dto;
    }
    public CommentEntity toEntity (CommentEntity entity, CommentDto dto) {
        entity.setCommentBy(userRepository.findOne(dto.getCommentBy()));
        entity.setCommentOn(bookRepository.findOne(dto.getCommentOn()));
        entity.setParentID(dto.getParentId());
        entity.setContent(dto.getContent());
        return entity;
    }
    public CommentEntity toEntity (CommentDto dto) {
        return toEntity(new CommentEntity(), dto);
    }
}