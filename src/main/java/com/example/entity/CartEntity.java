package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "cart")
@Getter
@Setter
public class CartEntity extends BaseEntity{

   @OneToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "userid")
   private UserEntity owner;

   @OneToMany (mappedBy = "cart" ,fetch = FetchType.LAZY)
   private List<BillDetailEntity> items;
}
