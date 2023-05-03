package com.fortuneprogramming.fashion_blog_api.dtos.commentdtos;


import com.fortuneprogramming.fashion_blog_api.models.Post;
import com.fortuneprogramming.fashion_blog_api.models.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateCommentDto {
    private String comment;
    private Timestamp createdAt;
    private User user;
    private Post post;
}
