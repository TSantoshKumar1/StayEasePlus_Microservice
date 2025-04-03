package com.service.user.service;

import com.service.user.entities.User;

import java.util.List;

public interface UserService {

    // create a user
    public User createUser(User user);

    // get all users
    public List<User> getAllUser();

    // get a single user
    public User getUser(String userId);
}
