package com.example.blogappweek9.Respositories;

import com.example.blogappweek9.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
