package com.stackroute.services;

import com.stackroute.model.User;

import java.util.Map;

public interface JwtTokenGenerator {
    Map<String,String> generateToken(User user);
}

