package com.example.blogappweek9.Controller;

import com.example.blogappweek9.DTO.PostDto;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Service.PostService;
import com.example.blogappweek9.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/new-post")
    public Post newPost(@RequestBody PostDto post) throws CustomException {

        return postService.savePost(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> allPosts() {
        List<Post> posts = postService.findAll();

        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(posts);

        }

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public Post Posts(@PathVariable("id") Long id){
        return postService.findById(id);
    }

    @PutMapping("/post-edit/{id}")
    public Post editPost(@PathVariable("id") Long id, @RequestBody PostDto post){

        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        try{
            String response = postService.deletePost(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("invalid operation", HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping("/{id}/likes")
    public int postLikes(@PathVariable("id") Long id){
        return postService.postLikes(id);
    }
}
