package com.example.blogappweek9.Respositories;

import com.example.blogappweek9.Model.Liked;
import com.example.blogappweek9.Model.Post;
import com.example.blogappweek9.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Liked,Long> {

    @Query(value = "SELECT * FROM likes WHERE id=?1 and post_id=?2",nativeQuery = true)

   Optional<Liked> findByUserIdAndPostId(Long userId, Long postId);

}
