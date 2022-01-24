package com.stackroute.services;

import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.model.User;
import com.stackroute.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        log.debug("inside saveUser()");
        User savedUser = null;
        Optional<User> saveUser = userRepo.findById(user.getEmail());
        try {
            if (saveUser.isPresent())
            {
                log.error("User is already present");
                throw new UserAlreadyExistsException("User with this email is already registered");
            }
            savedUser = userRepo.save(user);
        }catch (Exception ex)
        {
            log.error("exception occur" + ex.getMessage());
        }
        return savedUser;
    }

    @Override
    public User findUserByEmailId(String email) throws UserNotFoundException {
        User user = null;
        try {
            user = userRepo.findById(email).orElse(null);
            if (user!=null){
                return user;
            }
            else {
                throw new UserNotFoundException("User is not present");
            }
        }
        catch (Exception ex)
        {
            log.error("exception occur" + ex.getMessage());
        }
        return user;
    }

    @Override
    public boolean validateUser(User user) throws UserNotFoundException {
        User retriveUser = findUserByEmailId(user.getEmail());
        if (user.getPassword().equals(retriveUser.getPassword()))
        {
            return true;
        }
        else {
            return false;
        }
    }
}
