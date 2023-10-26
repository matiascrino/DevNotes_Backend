package com.example.backend_java.controllers;

import com.example.backend_java.dtos.UserDto;
import com.example.backend_java.models.requests.UserDetailRequestModel;
import com.example.backend_java.models.requests.UserLoginRequestModel;
import com.example.backend_java.models.responses.UserRest;
import com.example.backend_java.services.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth") //
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    UserServiceInterface userService;


    @PostMapping(value = "login")
    public UserRest login(@RequestBody UserLoginRequestModel userDetails){
        UserRest userToReturn = new UserRest();

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);

        UserDto loggedUser = userService.loginUser(userDto);

        BeanUtils.copyProperties(loggedUser, userToReturn);

        return userToReturn;

    }

    @PostMapping(value = "register" )
    public UserRest createUser(@RequestBody UserDetailRequestModel userDetails){
        UserRest userToReturn = new UserRest();

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser=userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, userToReturn);

        return userToReturn;
    }



}