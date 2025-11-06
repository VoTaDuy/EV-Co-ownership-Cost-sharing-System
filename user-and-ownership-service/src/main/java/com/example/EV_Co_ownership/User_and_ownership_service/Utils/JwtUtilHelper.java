package com.example.EV_Co_ownership.User_and_ownership_service.Utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtilHelper {
    @Value("${jwt.privateKey}")
    private String privateKey;

    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        return Jwts.builder().setSubject(data).signWith(key).compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public String getSubject(String token) {
        try {
            return extractAllClaims(token).getSubject();
        } catch (Exception e) {
            System.err.println("Lỗi giải mã token: " + e.getMessage());
            return null;
        }
    }

    public boolean verifyToken (String token){
        System.out.println("token received: " + token);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        System.out.println("Key: " + key);
        try{
            extractAllClaims(token).getSubject();
            System.out.println("success");
            return true;

        }catch (Exception ex){
            System.out.println("fall " + ex.getMessage());
            return false;
        }

    }

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
    }
}