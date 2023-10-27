package com.example.backend_java.auth.services;


import com.example.backend_java.auth.models.dtos.AuthDto;
import com.example.backend_java.entities.UserEntity;
import com.example.backend_java.auth.jwt.JwtService;
import com.example.backend_java.exceptions.BadRequestException;
import com.example.backend_java.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements AuthServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthDto loginUser(AuthDto user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            UserEntity userEntity = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found: " + user.getEmail()));
            AuthDto userToReturn = new AuthDto();
            BeanUtils.copyProperties(userEntity, userToReturn);
            userToReturn.setToken(jwtService.createToken(userToReturn.getEmail()));
            return userToReturn;
        }catch(Error error){
            throw new RuntimeException("Error al autenticar usuario");
        }

    }

    @Override
    public AuthDto createUser(AuthDto user) throws RuntimeException {

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("User already exists");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userEntity.setUserId(UUID.randomUUID().toString());

        UserEntity storedUserDetails = userRepository.save(userEntity);

        AuthDto userToReturn = new AuthDto();

        BeanUtils.copyProperties(storedUserDetails, userToReturn);

        userToReturn.setToken(jwtService.createToken(userToReturn.getEmail()));

        return userToReturn;

    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if(userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        UserEntity userEntity = userEntityOptional.get();

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

}
