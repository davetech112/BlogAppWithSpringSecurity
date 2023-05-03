package com.example.blogappweek9.Service;

import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.exception.CustomException;

import java.util.List;

public interface PostService {
    Post savePost(Post post, Long id) throws CustomException;
    List<Post> findAll();
    Post findById(Long id);
    Post updatePost(Long id, Post post, Long adminId);
    String deletePost(Long id, Long adminId);
    int postLikes(Long postId);
}
