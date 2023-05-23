package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private LikeService likeService;
    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("{id}/like")
    public ResponseEntity<String> addLike(@PathVariable("id") Long id,@RequestParam("like") boolean isliked){
        try{
            String response = likeService.addLikeToPost(id, isliked);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }
    }


}
