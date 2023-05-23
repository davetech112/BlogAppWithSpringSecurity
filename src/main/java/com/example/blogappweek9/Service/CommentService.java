package com.example.blogappweek9.Service;

import com.example.blogappweek9.Model.Comment;
import com.example.blogappweek9.Model.Post;

public interface CommentService {
    String addComment(Comment comment, Long postId);
}
