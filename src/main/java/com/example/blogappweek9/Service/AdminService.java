package com.example.blogappweek9.Service;

import com.example.blogappweek9.Model.User;

import java.util.List;

public interface AdminService {
    List<User> viewAllUsers(Long id);
    User editUserRole(Long id, String newRole, Long loggedInUserId);
    boolean banOrUnbanPost(Long id, Long postId);
    User blockUser(Long id, Long loggedInUserId, boolean blocked);
}
