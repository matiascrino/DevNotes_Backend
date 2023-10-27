package com.example.backend_java.auth.controllers;

import com.example.backend_java.auth.models.dtos.AuthDto;
import com.example.backend_java.auth.models.requests.UserDetailRequestModel;
import com.example.backend_java.auth.models.requests.UserLoginRequestModel;
import com.example.backend_java.auth.models.responses.AuthRest;
import com.example.backend_java.auth.services.AuthServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/auth") //
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthServiceInterface authService;

    @Autowired
    ObjectMapper objectMapper;


    @PostMapping(value = "login")
    public AuthRest login(@RequestBody @Valid UserLoginRequestModel userDetails){
        AuthDto userDto = objectMapper.convertValue(userDetails, AuthDto.class);

        AuthDto loggedUser = authService.loginUser(userDto);

        return objectMapper.convertValue(loggedUser, AuthRest.class);


    }

    @PostMapping(value = "register" )
    public AuthRest createUser(@RequestBody @Valid UserDetailRequestModel userDetails){
        AuthDto userDto = objectMapper.convertValue(userDetails, AuthDto.class);

        AuthDto createdUser=authService.createUser(userDto);

        return objectMapper.convertValue(createdUser, AuthRest.class);
    }



}