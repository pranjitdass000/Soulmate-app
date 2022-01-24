package com.stackroute.soulmateservice.controller;

import com.stackroute.soulmateservice.exception.ProfileAlreadyExistsException;
import com.stackroute.soulmateservice.model.User;
import com.stackroute.soulmateservice.model.UserProfile;
import com.stackroute.soulmateservice.repository.UserRepository;
import com.stackroute.soulmateservice.service.RabbitMqSender;
import com.stackroute.soulmateservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class UserController {

    private UserService userService;
    private ResponseEntity responseEntity;
    private RabbitMqSender rabbitMqSender;

    @Autowired
    public UserController(UserService userService,RabbitMqSender rabbitMqSender){

        this.userService=userService;
        this.rabbitMqSender=rabbitMqSender;

    }
//    @Autowired  /* field autowired */
//            UserRepository userRepository;



//    public UserRepository getUserRepository(){
//        return userRepository;
//    }
    @PostMapping("/createprofile")
    public ResponseEntity<UserProfile> saveUser(@Valid @RequestBody UserProfile userProfile) throws ProfileAlreadyExistsException {
        log.debug("Save request received for profile" + userProfile + "at " + java.time.LocalDateTime.now());
        try {
            log.info("profile details" + userProfile);
            UserProfile savedUser = userService.saveUser(userProfile);
            log.info("Saved user successfully" + savedUser);
            User user = new User();
            user.setEmail(userProfile.getEmail());
            user.setPassword(userProfile.getPassword());
            rabbitMqSender.sendMessageToRabbitMq(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
        catch (ProfileAlreadyExistsException e)
        {
            log.error("Exception occur" + e.getMessage());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            log.error("Exception occur" + e.getMessage());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;}
    @GetMapping("/users")
    public ResponseEntity<List<UserProfile>> getAllUsers() throws Exception {
        responseEntity = new ResponseEntity<List<UserProfile>>((List<UserProfile>) userService.getAllUsers(),HttpStatus.OK);
        return responseEntity;
    }

//        @GetMapping("/getprofile")
//    public ResponseEntity<List<UserProfile>>getAllUsers(){
//        List<UserProfile>retrievedUsers = userService.getAllUsers();
//        return new ResponseEntity<List<UserProfile>>(retrievedUsers,HttpStatus.OK);
//    }
//    @GetMapping("/searchgender/{gender}")
//    public ResponseEntity<List<UserProfile>>searchUserByGender(@PathVariable String gender){
//        List<UserProfile>retrievedUsers = userService.searchUserByGender(gender);
//        return new ResponseEntity<List<UserProfile>>(retrievedUsers,HttpStatus.OK);
    }


//    @DeleteMapping("/getprofile/{id}")
//
//    public  String deleteUser(@PathVariable String email){
//
//        userService.deleteUser(email);
//        return "deleted user with emailId" + email;
//
//    }



