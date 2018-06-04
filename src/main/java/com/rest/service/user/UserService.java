package com.rest.service.user;

import com.rest.model.User;

import java.util.List;

/**
 * Created by bimal on 2/17/18.
 */
public interface UserService {

    User findById(Long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    void deleteAllUsers();

    List<User> findAllUsers();

    boolean isUserExist(User user);

}
