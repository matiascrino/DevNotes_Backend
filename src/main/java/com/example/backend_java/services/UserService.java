package com.example.backend_java.services;

import com.example.backend_java.dtos.PostDto;
import com.example.backend_java.dtos.UserDto;
import com.example.backend_java.entities.PostEntity;
import com.example.backend_java.entities.UserEntity;
import com.example.backend_java.models.responses.ExpousureRest;
import com.example.backend_java.models.responses.UserRest;
import com.example.backend_java.repositories.PostRepository;
import com.example.backend_java.repositories.UserRepository;
import com.example.backend_java.services.interfaces.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ObjectMapper objectMapper;

    public UserDto getUser(String email) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if(userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        UserEntity userEntity = userEntityOptional.get();

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;

    }

    @Override
    public List<PostDto> getUserPosts(String email) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if(userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        UserEntity userEntity = userEntityOptional.get();

        List<PostEntity> posts = postRepository.getByUserIdOrderByCreatedAtDesc(userEntity.getId());

        List<PostDto> postDtos = new ArrayList<>();

        for(PostEntity postEntity : posts) {
            PostDto postDto = objectMapper.convertValue(postEntity, PostDto.class);
            postDto.setUser(objectMapper.convertValue(userEntity, UserRest.class));
            postDtos.add(postDto);
        }
        return postDtos;
    }

}
