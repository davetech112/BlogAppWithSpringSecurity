package com.example.blogappweek9.Service;

import com.example.blogappweek9.Model.Comment;
import com.example.blogappweek9.Model.Post;

public interface CommentService {
    String addComment(Long userId, Comment comment, Long postId);
}
