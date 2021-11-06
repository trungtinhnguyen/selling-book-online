package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "user")
@Getter
@Setter
public class UserEntity extends BaseEntity{

    @Column (name = "username")
    private String username;

    @Column (name = "password", columnDefinition = "TEXT")
    private String password;

    @Column (name = "fullname", columnDefinition = "VARCHAR(100)")
    private String fullName;

    @Column (name = "tell", columnDefinition = "CHAR(12)")
    private String tell;

    @Column (name = "address", columnDefinition = "TEXT")
    private String address;

    @Column (name = "email")
    private String email;

    @Column (name = "status")
    private int status;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "user_role", joinColumns = @JoinColumn(name = "userid"),
    inverseJoinColumns = @JoinColumn (name = "roleid"))
    private List<RoleEntity> roles;

    @OneToMany (mappedBy = "commentBy", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;
}
