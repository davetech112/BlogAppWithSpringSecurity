package com.example.blogappweek9.DTO;

import com.example.blogappweek9.Enum.Role;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private Role role;
}
