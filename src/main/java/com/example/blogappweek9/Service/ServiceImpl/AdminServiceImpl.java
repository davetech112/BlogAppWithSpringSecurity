package com.example.blogappweek9.Service.ServiceImpl;

import com.example.blogappweek9.Enum.Role;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.User;
import com.example.blogappweek9.Respositories.PostRepository;
import com.example.blogappweek9.Respositories.UserRepository;
import com.example.blogappweek9.Service.AdminService;
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
    public List<User> viewAllUsers(Long id){
        User loggedInUser = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No such user with id: "+id));
        if(loggedInUser.getRole().equals(Role.ADMIN)){
            return userRepository.findAll();
        }
        throw new RuntimeException("You are not an admin");
    }

    @Override
    public User editUserRole(Long id, String newRole, Long loggedInUserId) {
        User loggedInUser = userRepository.findById(loggedInUserId)
                .orElseThrow(()->new RuntimeException("no such user with id: "+loggedInUserId));
        if (loggedInUser.getRole().equals(Role.ADMIN)) {
            User userToEdit = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("user does not exist with id: "+id));
            if (newRole.equalsIgnoreCase("admin")) {
                userToEdit.setRole(Role.ADMIN);
            } else if (newRole.equalsIgnoreCase("user")) {
                userToEdit.setRole(Role.USER);
            } else {
                throw new RuntimeException("invalid input entered. Enter admin or user");
            }
            return userRepository.save(userToEdit);
        }
            throw new RuntimeException("You are not an admin");

    }

    @Override
    public boolean banOrUnbanPost(Long id, Long postId) {
        User loggedInUser = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no such user with id: "+id));
        if(loggedInUser.getRole().equals(Role.ADMIN)){
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
    public User blockUser(Long id, Long loggedInUserId, boolean blocked) {
        User loggedInUser = userRepository.findById(loggedInUserId)
                .orElseThrow(()->new RuntimeException("no such user with id: "+loggedInUserId));
        if(loggedInUser.getRole().equals(Role.ADMIN)){
            User user = userRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("user with id: "+id+" does not exist"));
            user.setBlocked(blocked);
            return userRepository.save(user);
        }
        throw new RuntimeException("You are not an admin");
    }
}
