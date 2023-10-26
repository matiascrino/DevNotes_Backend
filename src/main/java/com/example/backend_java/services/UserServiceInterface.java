package com.example.backend_java.services;


import com.example.backend_java.dtos.UserDto;

public interface UserServiceInterface  {
    public UserDto createUser(UserDto user);
    public UserDto loginUser(UserDto user);
}
