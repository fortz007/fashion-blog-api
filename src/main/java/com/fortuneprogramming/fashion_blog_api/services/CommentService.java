package com.fortuneprogramming.fashion_blog_api.services;



import com.fortuneprogramming.fashion_blog_api.dtos.commentdtos.CreateCommentDto;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotFoundException;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotNullException;
import com.fortuneprogramming.fashion_blog_api.utils.ApiResponse;
import com.fortuneprogramming.fashion_blog_api.models.Comment;

import java.util.List;

public interface CommentService {
    ApiResponse<Comment> createComment(CreateCommentDto createCommentDto, Long postId) throws NotNullException, NotFoundException;
    ApiResponse<List<Comment>> findAllPostComments(Long postId) throws NotFoundException;
    ApiResponse<Comment> updateComment(Long commentId, Comment comment) throws NotNullException;
    ApiResponse<String> deleteCommentById(Long commentId);
}
