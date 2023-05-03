package com.fortuneprogramming.fashion_blog_api.dtos.userdtos;

import com.fortuneprogramming.fashion_blog_api.enums.Role;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String gender;
    private Timestamp createdAt;
}
