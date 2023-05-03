package com.fortuneprogramming.fashion_blog_api.services;


import com.fortuneprogramming.fashion_blog_api.exceptions.NotFoundException;
import com.fortuneprogramming.fashion_blog_api.exceptions.UnauthorizedException;
import com.fortuneprogramming.fashion_blog_api.utils.ApiResponse;
import com.fortuneprogramming.fashion_blog_api.models.Like;

public interface LikeService {
    ApiResponse<Like> likeAPost(Long postId) throws UnauthorizedException;
    ApiResponse<String> unLikeAPost(Long postId) throws UnauthorizedException, NotFoundException;
    ApiResponse<Like> likeAComment(Long postId) throws UnauthorizedException;

    ApiResponse<String> unLikeAComment(Long commentId) throws UnauthorizedException, NotFoundException;
}
