package com.example.service.impl;

import com.example.constant.MessageKey;
import com.example.converter.BookConverter;
import com.example.dto.BaseDto;
import com.example.dto.BookDto;
import com.example.dto.CategoryDto;
import com.example.dto.CommentDto;
import com.example.entity.BillDetailEntity;
import com.example.entity.BookEntity;
import com.example.repository.BillDetailRepository;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import com.example.service.CategoryService;
import com.example.service.CommentService;
import com.example.util.MessageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final BillDetailRepository billDetailRepository;
    private final MessageUtils messageUtils;
    private final CommentService commentService;

    public BookServiceImpl(BookRepository bookRepository, BookConverter bookConverter, BillDetailRepository billDetailRepository, MessageUtils messageUtils, CommentService commentService) {
        this.bookConverter = bookConverter;
        this.bookRepository = bookRepository;
        this.billDetailRepository = billDetailRepository;
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
    public List<BookDto> findAll(Pageable pageable) {
        List<BookDto> results = new ArrayList<>();
        Page<BookEntity> page = bookRepository.findAll(pageable);
        List<BookEntity> entities = page.getContent();
        entities.forEach(entity -> results.add(bookConverter.toDto(entity)));
        return results;
    }

    @Override
    public List<BookDto> search(Pageable pageable, String searchText) {
       List<BookEntity> books = bookRepository.findByNameContains(searchText);
       List<BookDto> result = new ArrayList<>();
        books.forEach(book -> result.add(bookConverter.toDto(book)));
//       List<CategoryDto> categories = categoryService.findAll();
//       books.forEach(book -> {
//            if (book.getName().contains(searchText)) {
//                result.add(book);
//            } else {
//                for (CategoryDto category : categories) {
//                    if (category.getCode().equals(book.getCategoryCode()) && category.getName().contains(searchText)) {
//                        result.add(book);
//                        break;
//                    }
//                }
//            }
//       });
       return result;
    }

    @Override
    public BookDto findBestSeller() {
        BookDto result;
       List<BillDetailEntity> billDetails = billDetailRepository.findAll();
        Map<Long, Integer> map = new HashMap<>();
        billDetails.forEach(billDetail -> {
           if (billDetail.getBill() != null) {
               if (map.containsKey(billDetail.getBookIsBought().getId())) {
                   map.put(billDetail.getBookIsBought().getId(), map.get(billDetail.getBookIsBought().getId())+billDetail.getQuantity());
               } else {
                   map.put(billDetail.getBookIsBought().getId(), billDetail.getQuantity());
               }
           }
       });
        if (map.isEmpty()) {
            List<BookDto> books = findAll(new PageRequest(0,(int)bookRepository.count(), new Sort(Sort.Direction.DESC, "price")));
            result = books.get(0);
        } else {
            int max = 0;
            long bestSeller = 0;
            for (Map.Entry<Long, Integer> element : map.entrySet()) {
                if(element.getValue() > max) {
                    max = element.getValue();
                    bestSeller = element.getKey();
                }
            }
            result = bookConverter.toDto(bookRepository.findOne(bestSeller));
        }
        return result;
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
