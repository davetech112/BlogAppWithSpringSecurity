package com.example.blogappweek9.Service;

import com.example.blogappweek9.DTO.UserDTO;
import com.example.blogappweek9.Model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    String deleteUser(Long id, Long loggedInUserId);
    List<User> findAll();
    User loginUser(UserDTO userDTO);
}
