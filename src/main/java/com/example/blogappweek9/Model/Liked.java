package com.example.blogappweek9.Model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "likes")
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;

//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",referencedColumnName = "id") // use the correct column name
    private Post post;



}
