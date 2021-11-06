package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "bill")
@Getter
@Setter
public class BillEntity extends BaseEntity{

    @Column (name = "status")
    private int status;

    @Column (name = "modifieddate")
    private long modifiedDate;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "userid")
    private UserEntity user;

    @OneToMany (mappedBy = "bill", fetch = FetchType.LAZY)
    private List<BillDetailEntity> details;
}

