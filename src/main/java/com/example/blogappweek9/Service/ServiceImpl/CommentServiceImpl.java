package com.example.blogappweek9.Service.ServiceImpl;

import com.example.blogappweek9.Model.Comment;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.UserEntity;
import com.example.blogappweek9.Respositories.CommentRepository;
import com.example.blogappweek9.Respositories.PostRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.CommentService;
import com.example.blogappweek9.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public String addComment(Comment comment, Long postId) {
        UserEntity userEntity = userRepository.findByEmail(SecurityUtil.getSessionUser()).orElseThrow(()->new NoSuchElementException("User not Found"));
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        comment.setPost(post);
        comment.setUserEntity(userEntity);
        commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
        return "you successfully commented on post";
    }
}
