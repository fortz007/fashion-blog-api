package com.fortuneprogramming.fashion_blog_api.services.serviceImpl;

import com.fortuneprogramming.fashion_blog_api.dtos.userdtos.UserResponseDto;
import com.fortuneprogramming.fashion_blog_api.dtos.userdtos.UserSignupDto;
import com.fortuneprogramming.fashion_blog_api.enums.Role;
import com.fortuneprogramming.fashion_blog_api.exceptions.AlreadyExistsException;
import com.fortuneprogramming.fashion_blog_api.exceptions.NotFoundException;
import com.fortuneprogramming.fashion_blog_api.models.User;
import com.fortuneprogramming.fashion_blog_api.repositories.UserRepository;
import com.fortuneprogramming.fashion_blog_api.services.UserService;
import com.fortuneprogramming.fashion_blog_api.utils.ApiResponse;
import com.fortuneprogramming.fashion_blog_api.utils.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;

    @Override
    public boolean isEmailExist(String email) {
        boolean status;
        status = userRepository.existsByEmail(email);
        return status;
    }

    @Override
    public ApiResponse<UserResponseDto> signup(UserSignupDto userSignupDto) throws AlreadyExistsException {
        ApiResponse apiResponse;

        boolean emailExistStatus = isEmailExist(userSignupDto.getEmail());

        if(emailExistStatus == true){
            throw new AlreadyExistsException("Email already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(userSignupDto, user);
        user.setRole(Role.REGISTERED_USER);
        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        apiResponse = responseManager.success(userResponseDto);

        return apiResponse;
    }

    @Override
    public ApiResponse<UserResponseDto> login(String email, String password) throws NotFoundException {
        boolean isBloggerExistStatus = userRepository.existsByEmailAndPassword(email, password);

        if(isBloggerExistStatus == false)
            throw new NotFoundException("Invalid credentials");

        User user = userRepository.findByEmailAndPassword(email,password);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        httpSession.setAttribute("userId",user.getId());
        ApiResponse apiResponse = responseManager.success(userResponseDto);

        return apiResponse;
    }
}
