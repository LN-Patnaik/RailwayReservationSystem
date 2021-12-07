package com.UserComponent.Service;

import com.UserComponent.models.User;
import com.UserComponent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByUserId(String userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }

    @Override
    public User addUser(User user) {

        User addedUser = userRepository.save(user);
        return addedUser;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    public String deleteUserByUserId(String userId) {
        userRepository.deleteById(userId);
        return "Deleted Successfully";
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
