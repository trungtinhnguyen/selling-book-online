package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "billdetail")
@Getter
@Setter
public class BillDetailEntity extends BaseEntity{

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "price")
    private double price;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "orderid")
    private BillEntity bill;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "bookid")
    private BookEntity bookIsBought;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "cartid")
    private CartEntity cart;
}
