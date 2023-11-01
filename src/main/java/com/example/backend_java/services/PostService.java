package com.example.backend_java.services;

import com.example.backend_java.dtos.ExpousureDto;
import com.example.backend_java.dtos.PostCreationDto;
import com.example.backend_java.dtos.PostDto;
import com.example.backend_java.dtos.UserDto;
import com.example.backend_java.entities.ExpousureEntity;
import com.example.backend_java.entities.PostEntity;
import com.example.backend_java.entities.UserEntity;
import com.example.backend_java.models.responses.ExpousureRest;
import com.example.backend_java.models.responses.PostRest;
import com.example.backend_java.models.responses.UserRest;
import com.example.backend_java.repositories.ExpousureRepository;
import com.example.backend_java.repositories.PostRepository;
import com.example.backend_java.repositories.UserRepository;
import com.example.backend_java.services.interfaces.PostServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PostService implements PostServiceInterface {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpousureRepository expousureRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);



    @Override
    public PostDto createPost(PostCreationDto post) {

        UserEntity userEntity = userRepository.findByEmail(post.getUserEmail())
                .orElseThrow( () -> new RuntimeException("User not found"));

        ExpousureEntity expousureEntity = expousureRepository.findById(post.getExposureId())
                .orElseThrow( () -> new RuntimeException("Expousure not found"));

        PostEntity postEntity = mapToPostEntity(post, userEntity, expousureEntity);

        PostEntity savedPostEntity = postRepository.save(postEntity);

        PostDto postDto = objectMapper.convertValue(savedPostEntity, PostDto.class);

        postDto.setUser(objectMapper.convertValue(userEntity, UserRest.class));
        postDto.setExpousure(objectMapper.convertValue(expousureEntity, ExpousureRest.class));

        return postDto;
    }

    @Override
    public List<PostDto> getLastsPost() {
        long publicExpousureId = 2L;
        List<PostEntity> postEntities = postRepository.getLastPublicPosts(publicExpousureId);

        List<PostDto> postDtoList = new ArrayList<>();

        for(PostEntity postEntity : postEntities){
            PostDto postDto = objectMapper.convertValue(postEntity, PostDto.class);
            postDto.setUser(objectMapper.convertValue(postEntity.getUser(), UserRest.class));
            postDto.setExpousure(objectMapper.convertValue(postEntity.getExpousure(), ExpousureRest.class));
            postDtoList.add(postDto);
        }

        return postDtoList;

    }

    @Override
    public PostDto getPost(String postId) {
        PostEntity postEntity = postRepository.findByPostId(postId)
                .orElseThrow( () -> new RuntimeException("Post not found"));

        PostDto postDto = objectMapper.convertValue(postEntity, PostDto.class);

        postDto.setUser(objectMapper.convertValue(postEntity.getUser(), UserRest.class));

        postDto.setExpousure(objectMapper.convertValue(postEntity.getExpousure(), ExpousureRest.class));

        return postDto;


    }

    @Override
    public void deletePost(String postId, String userId) {
        PostEntity postEntity = postRepository.findByPostId(postId)
                .orElseThrow( () -> new RuntimeException("Post not found"));

        if(!(postEntity.getUser().getUserId().equals(userId))){
            throw new RuntimeException("You are not allowed to delete this post");
        }

        postRepository.delete(postEntity);


    }

    @Override
    public PostDto updatePost(String id, String userId, PostCreationDto postCreationDto) {
        PostEntity postEntity = postRepository.findByPostId(id)
                .orElseThrow( () -> new RuntimeException("Post not found"));

        if(!(postEntity.getUser().getUserId().equals(userId))){
            throw new RuntimeException("You are not allowed to update this post");
        }

        ExpousureEntity expousureEntity = expousureRepository.findById(postCreationDto.getExposureId())
                .orElseThrow( () -> new RuntimeException("Expousure not found"));

        postEntity.setExpousure(expousureEntity);
        postEntity.setTitle(postCreationDto.getTitle());
        postEntity.setContent(postCreationDto.getContent());
        postEntity.setExpiresAt(new Date(System.currentTimeMillis() + (postCreationDto.getExpiresAt() * 60000L)));

        PostDto postDto = objectMapper.convertValue(postRepository.save(postEntity), PostDto.class);

        postDto.setUser(objectMapper.convertValue(postEntity.getUser(), UserRest.class));

        return postDto;

    }

    private PostEntity mapToPostEntity(PostCreationDto post, UserEntity userEntity, ExpousureEntity expousureEntity) {
        PostEntity postEntity = new PostEntity();
        postEntity.setUser(userEntity);
        postEntity.setExpousure(expousureEntity);
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setPostId(UUID.randomUUID().toString());
        postEntity.setExpiresAt(new Date(System.currentTimeMillis() + (post.getExpiresAt() * 60000L)));
        return postEntity;
    }
}
