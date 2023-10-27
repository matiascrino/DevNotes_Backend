package com.example.backend_java.controllers;


import com.example.backend_java.auth.jwt.JwtAuthenticationFilter;
import com.example.backend_java.dtos.UserDto;
import com.example.backend_java.services.interfaces.UserServiceInterface;
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


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @GetMapping()
    public ResponseEntity<UserDto> getUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Useeeeee---, " + authentication);

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();


            UserDto userDto = userService.getUser(username);

            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

