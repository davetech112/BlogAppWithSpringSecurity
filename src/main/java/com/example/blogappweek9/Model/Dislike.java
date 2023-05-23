package com.example.blogappweek9.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dislike;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
}
