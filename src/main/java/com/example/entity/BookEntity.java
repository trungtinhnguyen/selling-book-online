package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "book")
@Getter
@Setter
public class BookEntity extends BaseEntity{

    @Column (name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column (name = "description", columnDefinition = "TEXT")
    private String description;

    @Column (name = "thumbnail")
    private String thumbnail;

    @Column (name = "cover", columnDefinition = "VARCHAR(100)")
    private String cover;

    @Column (name = "pagenumber")
    private int pageNumber;

    @Column (name = "price")
    private double price;

    @Column (name = "imputeddate")
    private long imputedDate;

    @Column (name = "publishedyear")
    private int publishedYear;

    @Column (name = "quantity")
    private int quantity;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "categoryid")
    private CategoryEntity category;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "publisherid")
    private PublisherEntity publisher;

    @OneToMany (mappedBy = "commentOn", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @OneToMany (mappedBy = "bookIsBought", fetch = FetchType.LAZY)
    private List<BillDetailEntity> billDetails;
}
