package com.fortuneprogramming.fashion_blog_api.dtos.postdtos;

import com.fortuneprogramming.fashion_blog_api.enums.DesignType;
import com.fortuneprogramming.fashion_blog_api.enums.DesignTypeGender;
import lombok.Data;

@Data
public class CreatePostDto {
    private String postTitle;
    private String postDescription;
    private DesignType designType;
    private DesignTypeGender designTypeGender;
}
