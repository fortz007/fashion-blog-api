package com.fortuneprogramming.fashion_blog_api.repositories;

import com.fortuneprogramming.fashion_blog_api.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long postId);
}
