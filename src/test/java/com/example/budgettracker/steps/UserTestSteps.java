package com.example.budgettracker.steps;

import com.example.budgettracker.service.UserService;
import com.example.budgettracker.transfer.user.CreateUserRequest;
import com.example.budgettracker.transfer.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@Component
public class UserTestSteps {

    @Autowired
    private UserService userService;

    public UserResponse createUser(){
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("TestUser");
        request.setFirstName("TestUserFirstName");
        request.setLastName("TestUserLastName");
        request.setEmailAddress("TestUser@test.com");
        request.setPassword("TestUserPassword");

        UserResponse user = userService.createUser(request);

        assertThat(user, notNullValue());
        assertThat(user.getId(), greaterThan(0L));
        assertThat(user.getUserName(), is(request.getUserName()));
        assertThat(user.getFirstName(), is(request.getFirstName()));
        assertThat(user.getLastName(), is(request.getLastName()));
        assertThat(user.getEmailAddress(), is(request.getEmailAddress()));
        assertThat(user.getPassword(), is(request.getPassword()));

        return user;
    }
}
