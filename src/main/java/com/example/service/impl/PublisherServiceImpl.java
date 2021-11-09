package com.example.service.impl;

import com.example.converter.PublisherConverter;
import com.example.dto.PublisherDto;
import com.example.repository.PublisherRepository;
import com.example.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherConverter publisherConverter;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherConverter publisherConverter) {
        this.publisherRepository = publisherRepository;
        this.publisherConverter = publisherConverter;
    }


    @Override
    public List<PublisherDto> findAll() {
       List<PublisherDto> dtos = new ArrayList<>();

       publisherRepository.findAll().forEach(entity-> {
           dtos.add(publisherConverter.toDto(entity));
       });
       return dtos;
    }
}
