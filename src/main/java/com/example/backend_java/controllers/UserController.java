package com.example.backend_java.controllers;


import com.example.backend_java.auth.jwt.JwtAuthenticationFilter;
import com.example.backend_java.dtos.PostDto;
import com.example.backend_java.dtos.UserDto;
import com.example.backend_java.models.responses.PostRest;
import com.example.backend_java.services.interfaces.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping()
    public ResponseEntity<UserDto> getUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();


            UserDto userDto = userService.getUser(username);

            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/posts") //localhost:8080/users/posts
    public List<PostRest> getPosts(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        logger.info("Controller, " + email + " is getting posts");

        List<PostDto> posts = userService.getUserPosts(email);

        List<PostRest> postsToReturn = new ArrayList<>();

        for(PostDto post : posts) {
            PostRest postRest = objectMapper.convertValue(post, PostRest.class);
            if(postRest.getExpiresAt().compareTo(new Date(System.currentTimeMillis())) < 0){
                postRest.setExpired(true);
            }
            postsToReturn.add(postRest);
        }

        return postsToReturn;

    }
}

