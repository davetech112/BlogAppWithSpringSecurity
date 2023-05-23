package com.example.blogappweek9.Service;

import com.example.blogappweek9.DTO.UserDTO;
import com.example.blogappweek9.Model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserDTO userEntity);
    String deleteUser(Long id);
    List<UserEntity> findAll();

}
