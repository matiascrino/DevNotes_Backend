package com.example.backend_java.auth.jwt;



import com.example.backend_java.auth.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtService {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);


    public String createToken(String email){

        return getToken(email);

    }

    private String getToken(String email) {
            return Jwts
                    .builder()
                    .setSubject(email)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE))
                    .signWith(getKey(), SignatureAlgorithm.HS256)
                    .compact();

    }

    private Key getKey() {
            return Keys.hmacShaKeyFor(SecurityConstants.getTokenSecret().getBytes());
    }

    public String getUsernameFromToken(String token) {
            return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    private Claims getClaims(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
            final Claims claims = getClaims(token);
            return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
            return getClaim(token, Claims::getExpiration);

    }

    private boolean isTokenExpired(String token){
            return getExpiration(token).before(new Date());
    }

}
