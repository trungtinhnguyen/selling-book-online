package com.example.service;

import com.example.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findByBookId (Long bookId);
    void delete (long[] ids);
}
