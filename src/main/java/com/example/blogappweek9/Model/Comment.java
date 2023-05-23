package com.example.blogappweek9.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @OneToOne
    private UserEntity userEntity;
    @ManyToOne
    private Post post;

}
