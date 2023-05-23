package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Service.DislikeService;
import com.example.blogappweek9.Service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dislike")
public class DislikeController {
    private DislikeService dislikeService;
    @Autowired
    public DislikeController(DislikeService dislikeService) {
        this.dislikeService = dislikeService;
    }

    @PostMapping("{id}/dislike")
    public ResponseEntity<String> addLike(@PathVariable("id") Long id,@RequestParam("dislike") boolean isdisliked){
        try{
            String response = dislikeService.addDislikeToPost(id, isdisliked);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }

    }
}
