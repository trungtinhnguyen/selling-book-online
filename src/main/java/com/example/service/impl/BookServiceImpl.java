package com.example.service.impl;

import com.example.constant.MessageKey;
import com.example.converter.BookConverter;
import com.example.dto.BaseDto;
import com.example.dto.BookDto;
import com.example.dto.CommentDto;
import com.example.entity.BookEntity;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import com.example.service.CommentService;
import com.example.util.FileUtils;
import com.example.util.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final MessageUtils messageUtils;
    private final CommentService commentService;

    public BookServiceImpl(BookRepository bookRepository, BookConverter bookConverter, MessageUtils messageUtils, CommentService commentService) {
        this.bookConverter = bookConverter;
        this.bookRepository = bookRepository;
        this.messageUtils = messageUtils;
        this.commentService = commentService;
    }

    @Override
    public BookDto findOne(Long id) {
        BookDto dto;
        BookEntity entity = bookRepository.findOne(id);
        if (entity != null) {
            dto = bookConverter.toDto(entity);
        } else {
            dto = new BookDto();
        }
        return dto;
    }

    @Override
    @Transactional
    public BookDto save(BookDto dto) {
        BookEntity entity;
        BookDto result;
//        FileUtils fileUtils = FileUtils.getInstance();
//        StringBuilder thumbnailPath = new StringBuilder(fileUtils.getUploadFolder());
        Map<String, String> message;
        if (dto.getId() == null) {
//            thumbnailPath.append("/book-").append(bookRepository.count()+1).append(".jpg");
            dto.setImputedDate(new Date(System.currentTimeMillis()));
            message = messageUtils.loadMessage(MessageKey.ADD_SUCCESS);
        } else {
            BookEntity old = bookRepository.findOne(dto.getId());
//            thumbnailPath.replace(0, thumbnailPath.length(), old.getThumbnail());
            dto.setId(old.getId());
            dto.setImputedDate(new Date(old.getImputedDate()));
            message = messageUtils.loadMessage(MessageKey.UPDATED_SUCCESS);
        }
//        fileUtils.saveFile(thumbnailPath.toString(), Base64.getDecoder().decode(dto.getThumbnail().getBytes()));
//        dto.setThumbnail(thumbnailPath.toString());
        entity = bookConverter.toEntity(dto);
        result = bookConverter.toDto(bookRepository.save(entity));
        result.setMessage(message.get(MessageUtils.MESSAGE));
        result.setType(message.get(MessageUtils.TYPE));
        result.setSuccess(true);
        return result;
    }

    @Override
    public List<BookDto> findAll() {
       List<BookDto> results = new ArrayList<>();
       List<BookEntity> entities = bookRepository.findAll();
       entities.forEach(entity -> {
           results.add(bookConverter.toDto(entity));
       });
       return results;
    }

    @Override
    @Transactional
    public void delete(long id) {
        List<CommentDto> comments = commentService.findByBookId(id);
        long[] commentIds =  comments.stream().mapToLong(BaseDto::getId).toArray();
        commentService.delete(commentIds);
        bookRepository.delete(id);
    }
}
