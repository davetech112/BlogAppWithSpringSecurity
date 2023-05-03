package com.example.blogappweek9.Service.ServiceImpl;

import com.example.blogappweek9.Model.Dislike;
import com.example.blogappweek9.Model.Liked;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.User;
import com.example.blogappweek9.Respositories.DislikeRepository;
import com.example.blogappweek9.Respositories.PostRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.DislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DislikeServiceImpl implements DislikeService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private DislikeRepository dislikeRepository;
    @Autowired
    public DislikeServiceImpl(PostRepository postRepository, UserRepository userRepository, DislikeRepository dislikeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.dislikeRepository = dislikeRepository;
    }



    @Override
    public String addDislikeToPost(Long postId, Long userId, boolean disliked) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Dislike> dislikeOptional = dislikeRepository.findByUserAndPost(user, post);

        if (disliked) {
            if (dislikeOptional.isPresent()) {
                return "You already disliked this post";
            } else {
                Dislike dislike = new Dislike();
                dislike.setPost(post);
                dislike.setUser(user);
                dislikeRepository.save(dislike);
                post.getDislikes().add(dislike);
                postRepository.save(post);
                return "You successfully disliked this post";
            }
        } else {
            if (dislikeOptional.isPresent()) {
                Dislike dislike = dislikeOptional.get();
                dislikeRepository.delete(dislike);
                post.getDislikes().remove(dislike);
                postRepository.save(post);
                return "You successfully undisliked this post";
            } else {
                return "You have not disliked this post yet";
            }
        }
    }
}
