package com.example.backend_java.services;

import com.example.backend_java.dtos.UserDto;
import com.example.backend_java.entities.UserEntity;
import com.example.backend_java.repositories.UserRepository;
import com.example.backend_java.services.interfaces.UserServiceInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

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

}
