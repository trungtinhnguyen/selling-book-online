package com.example.service.impl;

import com.example.converter.CommentConverter;
import com.example.dto.CommentDto;
import com.example.entity.BookEntity;
import com.example.entity.CommentEntity;
import com.example.repository.BookRepository;
import com.example.repository.CommentRepository;
import com.example.service.BookService;
import com.example.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final CommentConverter commentConverter;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository, CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.commentConverter = commentConverter;
    }

    @Override
    public List<CommentDto> findByBookId(Long bookId) {
        BookEntity book = bookRepository.findOne(bookId);
        List<CommentEntity> entities = commentRepository.findByCommentOn(book);
        List<CommentDto> dtos = new ArrayList<>();
        entities.forEach(entity -> {
            dtos.add(commentConverter.toDto(entity));
        });
        return dtos;
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            commentRepository.delete(id);
        }
    }
}
