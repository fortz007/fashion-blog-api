package com.fortuneprogramming.fashion_blog_api.services.serviceImpl;

import com.fortuneprogramming.fashion_blog_api.dtos.commentdtos.CreateCommentDto;
import com.fortuneprogramming.fashion_blog_api.enums.Role;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotFoundException;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotNullException;
import com.fortuneprogramming.fashion_blog_api.models.Comment;
import com.fortuneprogramming.fashion_blog_api.models.Post;
import com.fortuneprogramming.fashion_blog_api.models.User;
import com.fortuneprogramming.fashion_blog_api.repositories.CommentRepository;
import com.fortuneprogramming.fashion_blog_api.repositories.PostRepository;
import com.fortuneprogramming.fashion_blog_api.services.CommentService;
import com.fortuneprogramming.fashion_blog_api.utils.ApiResponse;
import com.fortuneprogramming.fashion_blog_api.utils.LoggedInUser;
import com.fortuneprogramming.fashion_blog_api.utils.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final HttpSession httpSession;
    private final PostRepository postRepository;
    private final LoggedInUser loggedInUser;
    private final ResponseManager responseManager;

    @Override
    public ApiResponse<Comment> createComment(CreateCommentDto createCommentDto, Long postId) throws NotNullException, NotFoundException {
        if(createCommentDto.getComment().equals(""))
            throw new NotNullException("Please type in a comment");

        Comment comment = new Comment();
        BeanUtils.copyProperties(createCommentDto,comment);
        if(httpSession.getAttribute("userId") == null){
            User user = new User(Role.ANONYMOUS_USER);
            comment.setUser(user);
        } else {
            comment.setUser(loggedInUser.findLoggedInUser());
        }

        Post post = postRepository.findById(postId).get();
        if(post == null){
            throw new NotFoundException("This post is not available");
        }
        comment.setPost(post);

        commentRepository.save(comment);
        return responseManager.success(comment);
    }

    @Override
    public ApiResponse<List<Comment>> findAllPostComments(Long postId) throws NotFoundException {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        if(comments.size() == 0)
            throw new NotFoundException("No comments for this post yet");
        return responseManager.success(comments);
    }

    @Override
    public ApiResponse<Comment> updateComment(Long commentId, Comment newComment) throws NotNullException {
        if(newComment.equals(""))
            throw new NotNullException("Please type in a comment");
        Comment comment = commentRepository.findById(commentId).get();
        comment.setComment(newComment.getComment());
        commentRepository.save(comment);
        return responseManager.success(comment);
    }

    @Override
    public ApiResponse<String> deleteCommentById(Long commentId){
        commentRepository.deleteById(commentId);
        return responseManager.success("Deleted Successfully");
    }
}
