package com.example.blogappweek9.Service.ServiceImpl;

import com.example.blogappweek9.Enum.Role;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.UserEntity;
import com.example.blogappweek9.Respositories.PostRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.AdminService;
import com.example.blogappweek9.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<UserEntity> viewAllUsers(){
        UserEntity loggedInUserEntity = userRepository.findByEmail(SecurityUtil.getSessionUser())
                .orElseThrow(()->new RuntimeException("No such user with id: "));
        if(loggedInUserEntity.getRole().equals(Role.ADMIN)){
            return userRepository.findAll();
        }
        throw new RuntimeException("You are not an admin");
    }

    @Override
    public UserEntity editUserRole(Long id, String newRole) {
        UserEntity loggedInUserEntity = userRepository.findByEmail(SecurityUtil.getSessionUser())
                .orElseThrow(()->new RuntimeException("no such user with id: "));
        if (loggedInUserEntity.getRole().equals(Role.ADMIN)) {
            UserEntity userEntityToEdit = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("user does not exist with id: "+id));
            if (newRole.equalsIgnoreCase("admin")) {
                userEntityToEdit.setRole(Role.ADMIN);
            } else if (newRole.equalsIgnoreCase("user")) {
                userEntityToEdit.setRole(Role.USER);
            } else {
                throw new RuntimeException("invalid input entered. Enter admin or user");
            }
            return userRepository.save(userEntityToEdit);
        }
            throw new RuntimeException("You are not an admin");

    }

    @Override
    public boolean banOrUnbanPost(Long postId) {
        UserEntity loggedInUserEntity = userRepository.findByEmail(SecurityUtil.getSessionUser())
                .orElseThrow(()->new RuntimeException("no such user with id: "));
        if(loggedInUserEntity.getRole().equals(Role.ADMIN)){
            Post post = postRepository.findById(postId)
                    .orElseThrow(()-> new RuntimeException("post does not exist with id: "+postId));
            if(post.isBanned()){
                post.setBanned(!post.isBanned());
            } else{
                post.setBanned(post.isBanned());
            }
            postRepository.save(post);
            return post.isBanned();
        }
        throw new RuntimeException("You are not an admin");
    }

    @Override
    public UserEntity blockUser(Long id, boolean blocked) {
        UserEntity loggedInUserEntity = userRepository.findByEmail(SecurityUtil.getSessionUser())
                .orElseThrow(()->new RuntimeException("no such user with id: "));
        if(loggedInUserEntity.getRole().equals(Role.ADMIN)){
            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("user with id: "+id+" does not exist"));
            userEntity.setBlocked(blocked);
            return userRepository.save(userEntity);
        }
        throw new RuntimeException("You are not an admin");
    }
}
