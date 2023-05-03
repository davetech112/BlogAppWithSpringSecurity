package com.example.blogappweek9.Service.ServiceImpl;

import com.example.blogappweek9.Model.Liked;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.User;
import com.example.blogappweek9.Respositories.LikeRepository;
import com.example.blogappweek9.Respositories.PostRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    @Autowired
    public LikeServiceImpl(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public String addLikeToPost(Long postId, Long userId, boolean isliked) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Liked> likeOptional = likeRepository.findByUserAndPost(user, post);

        if (isliked) {
            if (likeOptional.isPresent()) {
                return "You already liked this post";
            } else {
                Liked like = new Liked();
                like.setPost(post);
                like.setUser(user);
                likeRepository.save(like);
                post.getLikes().add(like);
                postRepository.save(post);
                return "You successfully liked this post";
            }
        } else {
            if (likeOptional.isPresent()) {
                Liked like = likeOptional.get();
                likeRepository.delete(like);
                post.getLikes().remove(like);
                postRepository.save(post);
                return "You successfully unliked this post";
            } else {
                return "You have not liked this post yet";
            }
        }
    }
}
