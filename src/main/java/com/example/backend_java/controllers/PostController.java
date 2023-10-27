package com.example.backend_java.controllers;


import com.example.backend_java.models.PostCreateRequestModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    @PostMapping
    public String createPost(@RequestBody @Valid PostCreateRequestModel createRequestModel){
        return createRequestModel.getTitle();
    }

}
