package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Model.Comment;
import com.example.blogappweek9.Service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post/{id}")
public class CommentController {
    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment-new")
    public String addComment(@PathVariable("id") Long postId, @RequestBody Comment comment
            , HttpServletRequest httpServletRequest){
        System.out.println("here");
        HttpSession session = httpServletRequest.getSession();
        Long userId = (Long)session.getAttribute("id");
        return commentService.addComment(userId,comment,postId);
    }
}
