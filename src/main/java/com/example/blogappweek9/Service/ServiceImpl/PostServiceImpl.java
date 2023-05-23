package com.example.blogappweek9.Service.ServiceImpl;

import com.example.blogappweek9.DTO.PostDto;
import com.example.blogappweek9.DTO.UserDTO;
import com.example.blogappweek9.Enum.Role;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.UserEntity;
import com.example.blogappweek9.Respositories.PostRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.PostService;
import com.example.blogappweek9.config.SecurityUtil;
import com.example.blogappweek9.exception.CustomException;
import com.example.blogappweek9.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Post savePost(PostDto post) throws CustomException {

            UserEntity userEntity = userRepository.findByEmail(SecurityUtil.getSessionUser()).orElseThrow(() -> new NullPointerException("User does not exist"));


            if (userEntity.getRole().equals(Role.ADMIN) && !userEntity.isBlocked()) {
                Post post1 = new Post();
                post1.setTitle(post.getTitle());
                post1.setPost(post.getPost());
                post1.setUserEntity(userEntity);
                return postRepository.save(post1);
            }
         else{
            throw new CustomException("Unable to make new post. please contact admin");
        }
    }


    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(()->new NullPointerException("Post with id "+id+" does not exist"));
    }

    @Override
    public Post updatePost(Long id, PostDto post) {
        UserEntity userEntity = userRepository.findByEmail(SecurityUtil.getSessionUser())
                .orElseThrow(()->new RuntimeException("user with id "+" does not exist"));
        if(userEntity.getRole().equals(Role.ADMIN)) {
            Post currentPost = postRepository.findById(id).orElseThrow(() -> new NullPointerException("post does not exist"));
            currentPost.setTitle(post.getTitle());
            currentPost.setPost(post.getPost());
            return postRepository.save(currentPost);
        }
        throw new RuntimeException("unauthorized access");
    }

    @Override
    public String deletePost(Long id) {
        UserEntity userEntity = userRepository.findByEmail(SecurityUtil.getSessionUser())
                .orElseThrow(()->new RuntimeException("user with id "+" does not exist"));
        if(userEntity.getRole().equals(Role.ADMIN)) {
            //if(delete.equals(true))
            postRepository.deleteById(id);
            return "post deleted successfully";
        }
        throw  new RuntimeException("unauthorized access");
    }

    @Override
    public int postLikes(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new EntityNotFoundException("No such post exists"));
        return  post.getLikes().size();
    }


}
