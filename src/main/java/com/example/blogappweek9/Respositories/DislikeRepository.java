package com.example.blogappweek9.Respositories;

import com.example.blogappweek9.Model.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    @Query(value = "SELECT * FROM dislike WHERE id=?1 and post_id=?2",nativeQuery = true)
    Optional<Dislike> findbyIdAndPostID(Long userid, Long postId);
}
