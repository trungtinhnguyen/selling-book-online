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

    @Column (name = "code", columnDefinition = "CHAR(10)")
    private String code;

    @Column (name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @OneToMany (mappedBy = "category", fetch = FetchType.LAZY)
    private List<BookEntity> books;
}
