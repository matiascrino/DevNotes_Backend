package com.example.backend_java.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomExceptionHandler implements  AccessDeniedHandler, AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
        // Maneja el acceso denegado, puedes personalizar la respuesta de error aquí.
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Acceso denegado\", \"error\": \"403 Forbidden\"}");
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        // Maneja la autenticación fallida, puedes personalizar la respuesta de error aquí.

        String errorMessage = "Error de autenticación: " + ex.getMessage();


        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"" + errorMessage + "\", \"error\": \"401 Unauthorized\"}");
    }

}

