package com.stackroute.services;

import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.model.User;

public interface UserService {

    User saveUser (User user) throws UserAlreadyExistsException;
    User findUserByEmailId (String email) throws UserNotFoundException;
    boolean validateUser (User user) throws UserNotFoundException;
}
