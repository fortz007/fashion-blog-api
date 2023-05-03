package com.fortuneprogramming.fashion_blog_api.services;


import com.fortuneprogramming.fashion_blog_api.dtos.userdtos.UserResponseDto;
import com.fortuneprogramming.fashion_blog_api.dtos.userdtos.UserSignupDto;
import com.fortuneprogramming.fashion_blog_api.exceptions.AlreadyExistsException;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotFoundException;
import com.fortuneprogramming.fashion_blog_api.utils.ApiResponse;

public interface UserService {
    boolean isEmailExist(String email);
    ApiResponse<UserResponseDto> signup(UserSignupDto userSignupDto) throws AlreadyExistsException;
    ApiResponse<UserResponseDto> login(String email, String password) throws NotFoundException;
}
