package com.example.blogappweek9.Service;

public interface DislikeService {
    String addDislikeToPost(Long postId, Long userId, boolean disliked);
}
