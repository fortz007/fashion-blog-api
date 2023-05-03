package com.fortuneprogramming.fashion_blog_api.services;



import com.fortuneprogramming.fashion_blog_api.dtos.postdtos.CreatePostDto;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotFoundException;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotNullException;
import com.fortuneprogramming.fashion_blog_api.exceptions.UnauthorizedException;
import com.fortuneprogramming.fashion_blog_api.utils.ApiResponse;
import com.fortuneprogramming.fashion_blog_api.models.Post;

import java.util.List;

public interface PostService {
    ApiResponse<Post> createPost(CreatePostDto createPostDto) throws NotNullException, UnauthorizedException;
    ApiResponse<Post> findPostById(Long postId) throws NotFoundException, UnauthorizedException;
    ApiResponse<List<Post>> findAllPosts() throws NotFoundException, UnauthorizedException;
    ApiResponse<Post> updatePostById(Long postId, CreatePostDto createPostDto) throws NotFoundException, UnauthorizedException;
    void deletePostById(Long postId) throws UnauthorizedException, NotFoundException;
}
