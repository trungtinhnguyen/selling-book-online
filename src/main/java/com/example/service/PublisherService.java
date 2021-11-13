package com.example.service;

import com.example.dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    List<PublisherDto> findAll ();
    PublisherDto findByCode (String code);
}
