package com.rest.controller;

import com.rest.model.User;
import com.rest.service.user.UserService;
import com.rest.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bimal on 2/17/18.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    // --------- retrieve all users ---------------------------
    @RequestMapping(value = "/user/",method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(){

        logger.info("====== Getting All Users ==========");
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    // ---------- retrieve single user -------------------------

    @RequestMapping(value = "/user/{id}" , method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") long id){

        logger.info("======== Retrieve User {} ===========",id);
        User user = userService.findById(id);
        if (user == null){
            logger.error("====== User with id {} not found.",id);
            return new ResponseEntity( new CustomErrorType("User with id "+id+" not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // ------------ create new User -------------------------------------

    @RequestMapping(value="/user/",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user){

        logger.info("=== Creating a User:  {} ",user);

        if(userService.isUserExist(user)){
            logger.error("Unable to create User. A user with name {} already exists.",user.getName());
            return new ResponseEntity<Object>(new CustomErrorType("Unable to create user. A user with name "+user.getName()+" alredy exists."),HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<Object>(user,HttpStatus.CREATED);
    }

    // --------------- update user ----------------------------------------

    @RequestMapping(value="/user/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user){
        logger.info("==== updating User with id {} ====",id);

        User currentUser = userService.findById(id);
        if(currentUser == null){
            logger.error("===== Unable to update. User with id {} not found. =====",id);
            return new ResponseEntity<Object>(new CustomErrorType("Unable to update. User with id "+id+" not found."),HttpStatus.NOT_FOUND);
        }
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());

        userService.saveUser(currentUser);
        return new ResponseEntity<>(currentUser,HttpStatus.OK);
    }

    // --------------- Delete a User --------------------------------------

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){

        logger.info("====== Removing a user with id {} ====",id);

        User currentUser = userService.findById(id);
        if(currentUser == null){
            logger.error("==== Unable to delete it. User with id {} not found ======",id);
            return new ResponseEntity<Object>(new CustomErrorType("Unable to delete it. User with id "+id+" not found."),HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // --------------- Delete all Users ----------------------------------------

    @RequestMapping(value = "/user/",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllUsers(){

        logger.info("======== Deleting all users ==================");
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
