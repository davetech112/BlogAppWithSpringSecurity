package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/")
public class LikeController {
    private LikeService likeService;
    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("{id}/like")
    public String addLike(@PathVariable("id") Long id,@RequestParam("like") boolean isliked, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userId = (Long) session.getAttribute("id");
        return likeService.addLikeToPost(id, userId, isliked);
    }

}
