package com.example.budgettracker.service;

import com.example.budgettracker.domain.User;
import com.example.budgettracker.persistance.UserRepository;
import com.example.budgettracker.transfer.user.UserResponse;
import com.example.budgettracker.transfer.user.CreateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request){
        LOGGER.info("Creating user {}", request);

        User user = new User();
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmailAddress());
        user.setPassword(request.getPassword());
        user.setImageUrl(request.getImageUrl());

        User savedUser =userRepository.save(user);

        return mapUserResponse(savedUser);
    }

    private UserResponse mapUserResponse (User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmailAddress(user.getEmailAddress());
        userResponse.setPassword(user.getPassword());
        userResponse.setImageUrl(user.getImageUrl());

        return userResponse;
    }
}
