package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Model.Comment;
import com.example.blogappweek9.Service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<String> addComment(@PathVariable("postId") Long postId, @RequestBody Comment comment
            ){
       try{
            commentService.addComment(comment,postId);
            return new ResponseEntity<>("successful operation", HttpStatus.OK);
       } catch (Exception e){
           return new ResponseEntity<>("operation not successful", HttpStatus.BAD_REQUEST);
       }


    }
}
