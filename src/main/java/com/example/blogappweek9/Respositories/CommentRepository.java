package com.example.blogappweek9.Respositories;

import com.example.blogappweek9.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
