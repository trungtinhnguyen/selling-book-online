package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof BookDto) {
            if (((BookDto) o).getId().equals(this.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
