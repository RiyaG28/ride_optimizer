package com.project.rideOptimiser.service;

import com.project.rideOptimiser.entity.User;
import com.project.rideOptimiser.enums.Role;
import com.project.rideOptimiser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersByRole(Role role) {
     //   Role role = Role.valueOf(rolename);
        return userRepository.findByRole(role);
    }

    public User registerUser(User user) {
        // Additional validation or business logic before saving user
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        // Additional validation or business logic before updating user
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

}
