package com.example.blogappweek9.Service;

import com.example.blogappweek9.DTO.PostDto;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.exception.CustomException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    Post savePost(PostDto post) throws CustomException;
    List<Post> findAll();
    Post findById(Long id);
    Post updatePost(Long id, PostDto post);
    String deletePost(Long id);
    int postLikes(Long postId);

}
