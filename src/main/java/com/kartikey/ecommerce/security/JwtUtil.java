package com.kartikey.ecommerce.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    public static final String SECRET_KEY= "kartikey-secret-key-for-jwt-authentication";
    public static final long EXPIRATION_TIME= 1000*60*60*10;
    private final Key key= Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token){
        return parseToken(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            parseToken(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }

    private Jws<Claims> parseToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}