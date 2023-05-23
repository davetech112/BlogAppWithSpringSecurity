package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Model.UserEntity;
import com.example.blogappweek9.Service.AdminService;
import com.example.blogappweek9.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private AdminService adminService;
    private UserService userService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping("/view-users")
    public List<UserEntity> viewAllUsers(){
        return adminService.viewAllUsers();
    }

    @PutMapping("/edit-role/{id}")
    public ResponseEntity<String> editUserRole(@PathVariable("id") Long id
            , @RequestParam("role") String role){
        try{
            adminService.editUserRole(id, role);
            return new ResponseEntity<>("operation successful" , HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("invalid operation", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/ban-unban-post/{id}")
    public ResponseEntity<String> isBanned(@PathVariable("id") Long postId, HttpServletRequest httpServletRequest){
        try {


            boolean result = adminService.banOrUnbanPost(postId);
            return new ResponseEntity<>("The ban status of this post is " + result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("invalid operation", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/block-user/{id}")
    public ResponseEntity<String> blockUser(@PathVariable("id") Long id, @RequestParam("blocked") boolean blocked){

         try{
             adminService.blockUser(id, blocked);
             return new ResponseEntity<>("operation successful", HttpStatus.OK);
         } catch (Exception e){
             return new ResponseEntity<>("invalid operation", HttpStatus.BAD_REQUEST);
         }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        try{
            String userlog = userService.deleteUser(id);
            return new ResponseEntity<>(userlog, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("invalid operation", HttpStatus.BAD_REQUEST);
        }

    }

}
