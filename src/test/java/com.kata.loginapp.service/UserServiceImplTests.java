package com.kata.loginapp.service;

import com.kata.loginapp.dataaccess.UserRepository;
import com.kata.loginapp.domain.User;
import com.kata.loginapp.service.UserAlreadyExistsException;
import com.kata.loginapp.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    @Before
    public void setup(){
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);

    }

    @Test
    public void saveUser_verify_persisted_to_database(){
        User user = new User();
        user.setName("TestName");
        user.setPassword("Password123//");

        userService.save(user);

        verify(userRepository).save(user);
    }

    @Test(expected=UserAlreadyExistsException.class)
    public void saveUser_fail_on_duplicate_name(){
        User user = new User();
        user.setName("DuplicateUser");
        user.setPassword("Password123//");

        when(userRepository.findOne("DuplicateUser")).thenReturn(user);

        userService.save(user);
    }
}
