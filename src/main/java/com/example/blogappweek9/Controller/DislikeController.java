package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Service.DislikeService;
import com.example.blogappweek9.Service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
public class DislikeController {
    private DislikeService dislikeService;
    @Autowired
    public DislikeController(DislikeService dislikeService) {
        this.dislikeService = dislikeService;
    }

    @PostMapping("{id}/dislike")
    public String addLike(@PathVariable("id") Long id,@RequestParam("dislike") boolean isdisliked, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userId = (Long) session.getAttribute("id");
        return dislikeService.addDislikeToPost(id, userId, isdisliked);
    }
}
