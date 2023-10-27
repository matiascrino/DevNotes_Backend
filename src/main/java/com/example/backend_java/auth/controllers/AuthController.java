package com.example.backend_java.auth.controllers;

import com.example.backend_java.auth.models.dtos.AuthDto;
import com.example.backend_java.auth.models.requests.UserDetailRequestModel;
import com.example.backend_java.auth.models.requests.UserLoginRequestModel;
import com.example.backend_java.auth.models.responses.AuthRest;
import com.example.backend_java.auth.services.AuthServiceInterface;
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


    @PostMapping(value = "login")
    public AuthRest login(@RequestBody @Valid UserLoginRequestModel userDetails){
        AuthRest userToReturn = new AuthRest();

        AuthDto userDto = new AuthDto();

        BeanUtils.copyProperties(userDetails, userDto);

        AuthDto loggedUser = authService.loginUser(userDto);

        BeanUtils.copyProperties(loggedUser, userToReturn);

        return userToReturn;

    }

    @PostMapping(value = "register" )
    public AuthRest createUser(@RequestBody @Valid UserDetailRequestModel userDetails){
        AuthRest userToReturn = new AuthRest();

        AuthDto userDto = new AuthDto();

        BeanUtils.copyProperties(userDetails, userDto);

        AuthDto createdUser=authService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, userToReturn);

        return userToReturn;
    }



}