package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookDto extends BaseDto{
    private String name;
    private String description;
    private String thumbnail;
    private String cover;
    private int pageNumber;
    private double price;
    private Date imputedDate;
    private int publishedYear;
    private int quantity;
    private String CategoryCode;
    private String publisherCode;
}
