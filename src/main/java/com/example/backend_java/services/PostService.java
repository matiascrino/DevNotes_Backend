package com.example.backend_java.services;

import com.example.backend_java.dtos.PostCreationDto;
import com.example.backend_java.dtos.PostDto;
import com.example.backend_java.repositories.PostRepository;
import com.example.backend_java.services.interfaces.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService implements PostServiceInterface {

    @Autowired
    PostRepository postRepository;

    @Override
    public PostDto createPost(PostCreationDto post) {
        //PostRepository....
    }
}
