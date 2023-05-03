package com.fortuneprogramming.fashion_blog_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fortuneprogramming.fashion_blog_api.enums.DesignType;
import com.fortuneprogramming.fashion_blog_api.enums.DesignTypeGender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Post extends BaseEntity{
    private String postTitle;
    private String postDescription;

    @Enumerated(EnumType.STRING)
    private DesignType designType;

    @Enumerated(EnumType.STRING)
    private DesignTypeGender designTypeGender;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;
}
