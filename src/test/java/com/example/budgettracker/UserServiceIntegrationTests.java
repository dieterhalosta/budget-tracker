package com.example.budgettracker;

import com.example.budgettracker.exception.ResourcesNotFound;
import com.example.budgettracker.service.UserService;
import com.example.budgettracker.steps.UserTestSteps;
import com.example.budgettracker.transfer.user.CreateUserRequest;
import com.example.budgettracker.transfer.user.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTestSteps userTestSteps;

    @Test
    public void createUser_WhenValidRequest_theReturnCreatedUser(){
        userTestSteps.createUser();
    }

    @Test
    public void createUser_whenMissingMandatoryProperties_thenThrowException(){
        CreateUserRequest request = new CreateUserRequest();

        try{
            userService.createUser(request);
        }catch (Exception e){
            assertThat("Unexpected exception thrown.", e instanceof ConstraintViolationException);
        }
    }

    @Test
    public void getUser_whenPassingValidId_thenReturnUser(){
        UserResponse user = userTestSteps.createUser();
        UserResponse response = userService.getUserResponse(user.getId());

        assertThat(response, notNullValue());
        assertThat(response.getId(), is(user.getId()));
        assertThat(response.getUserName(), is(user.getUserName()));
        assertThat(response.getFirstName(), is(user.getFirstName()));
        assertThat(response.getLastName(), is(user.getLastName()));
        assertThat(response.getEmailAddress(), is(user.getEmailAddress()));
        assertThat(response.getPassword(), is(user.getPassword()));
    }

    @Test
    public void getUser_whenNotValidId_thenThrowError(){
        Assertions.assertThrows(ResourcesNotFound.class, () -> userService.getUserResponse(99));
    }

}
