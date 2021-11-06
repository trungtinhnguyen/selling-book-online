package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "comment")
@Getter
@Setter
public class CommentEntity extends BaseEntity{

    @Column (name = "commnet", columnDefinition = "TEXT")
    private String code;

    @Column (name = "parentid")
    private Long parentID;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "bookid")
    private BookEntity commentOn;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "userid")
    private UserEntity commentBy;
}
