package com.example.blogappweek9.Controller;

import com.example.blogappweek9.DTO.UserDTO;
import com.example.blogappweek9.Model.User;
import com.example.blogappweek9.Response.ApiResponse;
import com.example.blogappweek9.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO userDTO){
        User user = userService.loginUser(userDTO);
        log.info("ng");
        HttpSession session = request.getSession();
        session.setAttribute("id",user.getId());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        User users= userService.saveUser(user);
        return new ResponseEntity<>(users,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userLogged = (Long) session.getAttribute("id");
        String userlog = userService.deleteUser(id, userLogged);
        return new ResponseEntity<>(userlog,HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Logout Successful",HttpStatus.OK);
    }
}
