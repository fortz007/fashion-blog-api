package com.fortuneprogramming.fashion_blog_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private T message;
    private boolean success;
    private T data;
}

