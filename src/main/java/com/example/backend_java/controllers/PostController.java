package com.example.backend_java.controllers;


import com.example.backend_java.auth.jwt.JwtAuthenticationFilter;
import com.example.backend_java.dtos.PostCreationDto;
import com.example.backend_java.dtos.PostDto;
import com.example.backend_java.dtos.UserDto;
import com.example.backend_java.models.requests.PostCreateRequestModel;
import com.example.backend_java.models.responses.OperationStatusModel;
import com.example.backend_java.models.responses.PostRest;
import com.example.backend_java.services.interfaces.PostServiceInterface;
import com.example.backend_java.services.interfaces.UserServiceInterface;
import com.example.backend_java.utils.Expousures;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostServiceInterface postService;

    @Autowired
    UserServiceInterface userService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @PostMapping
    public PostRest createPost(@RequestBody @Valid PostCreateRequestModel createRequestModel){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        logger.info("Controller, " + email + " is creating a post");

        PostCreationDto postCreationDto = objectMapper.convertValue(createRequestModel, PostCreationDto.class);

        postCreationDto.setUserEmail(email);

        PostDto postDto =  postService.createPost(postCreationDto);

        PostRest postToReturn = objectMapper.convertValue(postDto, PostRest.class);

        if(postToReturn.getExpiresAt().compareTo(new Date(System.currentTimeMillis())) < 0){
            postToReturn.setExpired(true);
        }

        return postToReturn;

    }

    @GetMapping("/last")
    public List<PostRest> getLastsPost(){

        List<PostDto> postDtoList= postService.getLastsPost();

        List<PostRest> postsRest = new ArrayList<>();

        for(PostDto postDto : postDtoList){
            PostRest postRest = objectMapper.convertValue(postDto, PostRest.class);
            if(postRest.getExpiresAt().compareTo(new Date(System.currentTimeMillis())) < 0){
                postRest.setExpired(true);
            }
            postsRest.add(postRest);
        }

        return postsRest;
    }

    @GetMapping("getById/{id}")
    public PostRest getPost(@PathVariable String id){

        PostDto post = postService.getPost(id);

        PostRest postRest = objectMapper.convertValue(post, PostRest.class);

        if(postRest.getExpiresAt().compareTo(new Date(System.currentTimeMillis())) < 0){
            postRest.setExpired(true);
        }

        if(postRest.getExpousure().getExpousureId() == Expousures.PRIVATE) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            UserDto userDto = userService.getUser(email);

            logger.info("Controller, " + email + " is getting a post");
            logger.info("Controller, " + userDto + " is getting a post");

            if(!userDto.getUserId().equals(postRest.getUser().getUserId())){
                throw new RuntimeException("You are not allowed to see this post");
            }
        }

        return postRest;
    }

    @DeleteMapping("/deleteById/{id}")
    public OperationStatusModel deletePost(@PathVariable String id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto user = userService.getUser(authentication.getName());

        logger.info("Controller, " + user.getEmail() + " is deleting a post");

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setName("DELETE");

        postService.deletePost(id, user.getUserId());
        operationStatusModel.setResult("SUCCESS");

        return operationStatusModel;

    }

    @PutMapping("/updateById/{id}")
    public PostRest updatePost(@RequestBody PostCreateRequestModel postToUpdate, @PathVariable String id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDto user = userService.getUser(authentication.getName());

        PostCreationDto postCreationDto = objectMapper.convertValue(postToUpdate, PostCreationDto.class);

        PostDto postDto = postService.updatePost(id, user.getUserId(), postCreationDto);


        return objectMapper.convertValue(postDto, PostRest.class);

    }
}
