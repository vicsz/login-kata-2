package com.kata.loginapp.service;

import com.kata.loginapp.dataaccess.UserRepository;
import com.kata.loginapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        User existingUser = userRepository.findOne(user.getName());

        if (existingUser != null)
            throw new UserAlreadyExistsException("User already exists for id:" + user.getName());

        return userRepository.save(user);
    }
}
