package com.example.blogappweek9.Respositories;

import com.example.blogappweek9.Model.Dislike;
import com.example.blogappweek9.Model.Liked;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    Optional<Dislike> findByUserAndPost(User user, Post post);
}
