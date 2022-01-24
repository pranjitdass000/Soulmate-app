package com.stackroute.soulmateservice.service;

import com.stackroute.soulmateservice.exception.ProfileAlreadyExistsException;
import com.stackroute.soulmateservice.exception.ProfileNotFoundException;
import com.stackroute.soulmateservice.model.User;
import com.stackroute.soulmateservice.model.UserProfile;

import java.util.List;

public interface UserService {
    UserProfile saveUser(UserProfile userProfile) throws ProfileAlreadyExistsException;
    List<UserProfile> getAllUsers() throws Exception;
//    List<UserProfile> deleteUser(String email);
    List<UserProfile> searchUserByGender(String gender);
    UserProfile deleteUser(String email) throws ProfileNotFoundException;
    UserProfile updateUser(UserProfile profile) throws ProfileNotFoundException, ProfileAlreadyExistsException;
    UserProfile getUserByemail(String email) throws ProfileNotFoundException;
}
