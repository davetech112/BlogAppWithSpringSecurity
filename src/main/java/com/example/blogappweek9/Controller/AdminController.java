package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Model.User;
import com.example.blogappweek9.Service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/view-users")
    public List<User> viewAllUsers(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long loggedInUser = (Long) session.getAttribute("id");
        return adminService.viewAllUsers(loggedInUser);
    }

    @PutMapping("/edit-role/{id}")
    public User editUserRole(@PathVariable("id") Long id
            ,@RequestParam("role") String role,
                             HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long loggedInUser = (Long) session.getAttribute("id");
        return adminService.editUserRole(id, role, loggedInUser);
    }

    @PostMapping("/ban-unban-post/{id}")
    public String isBanned(@PathVariable("id") Long postId, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long loggedInUser = (Long) session.getAttribute("id");
        boolean result = adminService.banOrUnbanPost(loggedInUser, postId);
        return "The ban status of this post is "+ result;
    }

    @PutMapping("/block-user/{id}")
    public User blockUser(@PathVariable("id") Long id,@RequestParam("blocked") boolean blocked, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long loggedInUser = (Long) session.getAttribute("id");
        return adminService.blockUser(id, loggedInUser, blocked);
    }

}
