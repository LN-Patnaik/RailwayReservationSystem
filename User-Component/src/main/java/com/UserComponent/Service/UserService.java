package com.UserComponent.Service;

import com.UserComponent.models.User;

import java.util.List;

public interface UserService {

    public User getUserByUserId(String userId);

    public User addUser(User user);

    public User updateUser(User user);

    public String deleteUserByUserId(String userId);

    public List<User> findAllUsers();

}
