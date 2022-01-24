package com.stackroute.soulmateservice.bootstrap;

import com.stackroute.soulmateservice.model.UserProfile;
import com.stackroute.soulmateservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserBootStrap implements CommandLineRunner {
    private UserRepository userRepository;
    @Autowired
    public UserBootStrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserProfile userProfile1 = new UserProfile("p@gmail.com","pj","pj",24,"male","Ghy");
        UserProfile userProfile2 = new UserProfile("ppp@gmail.com","ppj","ppj",19,"female","New york");

        userRepository.save(userProfile1);
        System.out.println("save user profile 1");
        userRepository.save(userProfile2);
        System.out.println("save user profile 2");

    }

}
