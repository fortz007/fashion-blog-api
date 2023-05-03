package com.fortuneprogramming.fashion_blog_api.dtos.likedtos;


import com.fortuneprogramming.fashion_blog_api.models.Post;
import com.fortuneprogramming.fashion_blog_api.models.User;
import lombok.Data;

@Data
public class LikeAPostDto {
    private Long noOfLikes = 0L;
    private User user;
    private Post post;
}
