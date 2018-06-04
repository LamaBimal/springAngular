package com.rest.service.user.impl;

import com.rest.model.User;
import com.rest.repositories.UserRepository;
import com.rest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bimal on 2/17/18.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findOne(id);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user){
        saveUser(user);
    }

    public void deleteUserById(Long id){
        userRepository.delete(id);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public boolean isUserExist(User user){
        return  findByName(user.getName()) != null;
    }
}
