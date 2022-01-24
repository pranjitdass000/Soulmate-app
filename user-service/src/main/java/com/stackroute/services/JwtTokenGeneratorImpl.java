package com.stackroute.services;

import com.stackroute.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtTokenGeneratorImpl implements JwtTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        log.debug("Inside generate token");
        String jwtToken="";
        jwtToken= Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretkey").compact();
        Map<String, String> tokenMap=new HashMap<>();
        tokenMap.put("token",jwtToken);
        tokenMap.put("message","User successfully logged in");
        return tokenMap;
    }

}