package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "comment")
@Getter
@Setter
public class CommentEntity extends BaseEntity{

    @Column (name = "content", columnDefinition = "TEXT")
    private String content;

    @Column (name = "parentid")
    private Long parentID;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "bookid")
    private BookEntity commentOn;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "userid")
    private UserEntity commentBy;
}
