package com.example.backend_java.services.interfaces;

import com.example.backend_java.dtos.PostCreationDto;
import com.example.backend_java.dtos.PostDto;

public interface PostServiceInterface {
    public PostDto createPost(PostCreationDto post);
}
