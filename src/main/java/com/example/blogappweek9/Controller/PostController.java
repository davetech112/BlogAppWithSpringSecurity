package com.example.blogappweek9.Controller;

import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Service.PostService;
import com.example.blogappweek9.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/new-post")
    public Post newPost(@RequestBody Post post, HttpServletRequest httpServletRequest) throws CustomException {
        HttpSession session = httpServletRequest.getSession();
        Long loggedUser = (Long)session.getAttribute("id");
        return postService.savePost(post, loggedUser);
    }

    @GetMapping("/posts")
    public List<Post> allPosts(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post Posts(@PathVariable("id") Long id){
        return postService.findById(id);
    }

    @PutMapping("/post-edit/{id}")
    public Post editPost(@PathVariable("id") Long id, @RequestBody Post post, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long loggedUser = (Long)session.getAttribute("id");
        return postService.updatePost(id, post, loggedUser);
    }

    @DeleteMapping("/{id}/delete")
    public String deletePost(@PathVariable("id") Long id,
                           HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long loggedUser = (Long)session.getAttribute("id");
        return postService.deletePost(id, loggedUser);

    }
    @GetMapping("/{id}/likes")
    public int postLikes(@PathVariable("id") Long id){
        return postService.postLikes(id);
    }
}
