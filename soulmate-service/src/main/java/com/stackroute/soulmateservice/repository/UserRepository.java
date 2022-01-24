package com.stackroute.soulmateservice.repository;

import com.stackroute.soulmateservice.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends MongoRepository<UserProfile,String> {
    List<UserProfile> findByGender(String gender);
//    List<UserProfile> deleteUser(String email);
}
