package com.example.blogappweek9.Service;

import com.example.blogappweek9.Model.UserEntity;

import java.util.List;

public interface AdminService {
    List<UserEntity> viewAllUsers();
    UserEntity editUserRole(Long id, String newRole);
    boolean banOrUnbanPost(Long postId);
    UserEntity blockUser(Long id, boolean blocked);
}
