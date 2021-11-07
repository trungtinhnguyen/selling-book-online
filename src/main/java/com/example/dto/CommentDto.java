package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto extends BaseDto{
    private String content;
    private Long parentId;
    private Long commentOn;
    private Long commentBy;
}
