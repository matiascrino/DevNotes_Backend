package com.example.backend_java.services.interfaces;

import com.example.backend_java.dtos.PostCreationDto;
import com.example.backend_java.dtos.PostDto;

import java.util.List;

public interface PostServiceInterface {
    public PostDto createPost(PostCreationDto post);

    List<PostDto> getLastsPost();


    PostDto getPost(String id);


    void deletePost(String userId, String id);

    PostDto updatePost(String id, String userId, PostCreationDto postCreationDto);

}
