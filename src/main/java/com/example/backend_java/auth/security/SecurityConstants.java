package com.example.backend_java.auth.security;

import com.example.backend_java.context.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_DATE = 864000000; // 10 dias
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret(){
        ApplicationProperties applicationProperties = (ApplicationProperties) SpringApplicationContext.getBean("ApplicationProperties");
        return applicationProperties.getSecretToken();
    }

}
