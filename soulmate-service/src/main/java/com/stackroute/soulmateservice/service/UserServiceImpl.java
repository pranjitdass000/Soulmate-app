package com.stackroute.soulmateservice.service;

import com.stackroute.soulmateservice.exception.ProfileAlreadyExistsException;
import com.stackroute.soulmateservice.exception.ProfileNotFoundException;
import com.stackroute.soulmateservice.model.User;
import com.stackroute.soulmateservice.model.UserProfile;
import com.stackroute.soulmateservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;


    @Autowired
    private RabbitMqSender rabbitMqSender;



    @Autowired
    public UserServiceImpl(UserRepository userRepository){

        this.userRepository=userRepository;
    }
    @Override
    public UserProfile saveUser(UserProfile userProfile) throws ProfileAlreadyExistsException{
        log.debug("Inside saveUser()");
        UserProfile savedUser = new UserProfile();
        Optional<UserProfile> saveUser = userRepository.findById(userProfile.getEmail());
        if (saveUser.isPresent())
        {
            log.error("Profile is already present");
            throw new ProfileAlreadyExistsException();
        }
        savedUser = userRepository.save(userProfile);
        return savedUser;
    }

    @Override
    public List<UserProfile> getAllUsers() {
        List<UserProfile> userList =userRepository.findAll();
        return userList;
    }

    @Override
    public List<UserProfile> searchUserByGender(String gender) {
        List<UserProfile> userProfileList = userRepository.findByGender(gender);
        return userProfileList;
    }

    @Override
    public UserProfile deleteUser(String email) throws ProfileNotFoundException {
        return null;
    }

    @Override
    public UserProfile updateUser(UserProfile profile) throws ProfileNotFoundException, ProfileAlreadyExistsException {
        return null;
    }

    @Override
    public UserProfile getUserByemail(String email) throws ProfileNotFoundException {
        return null;
    }
}
