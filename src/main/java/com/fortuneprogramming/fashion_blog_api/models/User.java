package com.fortuneprogramming.fashion_blog_api.models;

import com.fortuneprogramming.fashion_blog_api.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(schema = "public")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String gender;

    public User(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Post> posts;

    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Comment> comments;

    @OneToMany(mappedBy ="user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Like> likes;
}
