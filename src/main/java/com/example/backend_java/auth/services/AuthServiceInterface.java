package com.example.backend_java.auth.services;


import com.example.backend_java.auth.models.dtos.AuthDto;

public interface AuthServiceInterface {
    public AuthDto createUser(AuthDto user);
    public AuthDto loginUser(AuthDto user);
}
