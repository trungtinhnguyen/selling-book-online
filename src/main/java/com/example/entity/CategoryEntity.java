package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "category")
@Getter
@Setter
public class CategoryEntity extends BaseEntity{

    @Column (name = "code", columnDefinition = "CHAR(100)")
    private String code;

    @Column (name = "name")
    private String name;

    @OneToMany (mappedBy = "category", fetch = FetchType.LAZY)
    private List<BookEntity> books;
}
