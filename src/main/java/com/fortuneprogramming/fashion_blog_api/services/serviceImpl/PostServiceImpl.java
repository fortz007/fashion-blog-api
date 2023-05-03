package com.fortuneprogramming.fashion_blog_api.services.serviceImpl;

import com.fortuneprogramming.fashion_blog_api.dtos.postdtos.CreatePostDto;
import com.fortuneprogramming.fashion_blog_api.enums.Role;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotFoundException;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotNullException;
import com.fortuneprogramming.fashion_blog_api.exceptions.UnauthorizedException;
import com.fortuneprogramming.fashion_blog_api.models.Post;
import com.fortuneprogramming.fashion_blog_api.repositories.PostRepository;
import com.fortuneprogramming.fashion_blog_api.services.PostService;
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
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;
    private final LoggedInUser loggedInUser;

    @Override
    public ApiResponse<Post> createPost(CreatePostDto createPostDto) throws NotNullException, UnauthorizedException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to carry out this operation");
        if(loggedInUser.findLoggedInUser().getRole() != Role.BLOGGER)
            throw new UnauthorizedException("You're not authorized to carry out this operation");
        if(createPostDto.getPostTitle().equals("") || createPostDto.getPostDescription().equals("") || createPostDto.getDesignType() == null || createPostDto.getDesignTypeGender() == null)
            throw new NotNullException("You're missing one of the required inputs");

        Post post = new Post();
        BeanUtils.copyProperties(createPostDto,post);
        post.setUser(loggedInUser.findLoggedInUser());
        postRepository.save(post);
        return responseManager.success(post);
    }

    @Override
    public ApiResponse<Post> findPostById(Long postId) throws NotFoundException {
        if(postRepository.existsById(postId) == false)
            throw new NotFoundException("No such post");
        Post post = postRepository.findById(postId).get();
        return responseManager.success(post);
    }

    @Override
    public ApiResponse<List<Post>> findAllPosts() throws NotFoundException {
        List<Post> allPosts = postRepository.findAll();
        if(allPosts.size() == 0)
            throw new NotFoundException("No posts yet");
        return responseManager.success(allPosts);
    }

    @Override
    public ApiResponse<Post> updatePostById(Long postId, CreatePostDto createPostDto) throws NotFoundException, UnauthorizedException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to carry out this operation");
        if(loggedInUser.findLoggedInUser().getRole() != Role.BLOGGER)
            throw new UnauthorizedException("You're not authorized to carry out this operation");
        if(postRepository.existsById(postId) == false)
            throw new NotFoundException("No such post");

        Post post = postRepository.findById(postId).get();
        BeanUtils.copyProperties(createPostDto,post);
        postRepository.save(post);
        return responseManager.success(post);
    }

    @Override
    public void deletePostById(Long postId) throws UnauthorizedException, NotFoundException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to carry out this operation");
        if(loggedInUser.findLoggedInUser().getRole() != Role.BLOGGER)
            throw new UnauthorizedException("You're not authorized to carry out this operation");
        if(postRepository.existsById(postId) == false)
            throw new NotFoundException("No such post");
        postRepository.deleteById(postId);
    }
}
