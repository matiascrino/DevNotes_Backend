package com.example.backend_java.services.interfaces;

import com.example.backend_java.auth.models.dtos.AuthDto;
import com.example.backend_java.dtos.PostDto;
import com.example.backend_java.dtos.UserDto;

import java.util.List;

public interface UserServiceInterface {
    UserDto getUser(String email);

    List<PostDto> getUserPosts(String email);


}
