package com.example.backend_java.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Autowired
    private Environment env;

    public String getSecretToken() {
        return env.getProperty("token_secret");
    }
}
