package com.example.blogappweek9.Service;

public interface LikeService {
    String addLikeToPost(Long postId, Long userId, boolean liked);
}
