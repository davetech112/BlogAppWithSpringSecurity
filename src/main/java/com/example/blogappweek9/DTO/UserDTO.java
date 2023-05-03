package com.example.blogappweek9.DTO;

import com.example.blogappweek9.Enum.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private boolean blocked = false;
}
