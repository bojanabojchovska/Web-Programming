package org.example.cinema.service.impl;

import org.example.cinema.model.User;
import org.example.cinema.model.exceptions.InvalidArgumentsException;
import org.example.cinema.model.exceptions.InvalidUsernameOrPasswordException;
import org.example.cinema.repository.UserRepository;
import org.example.cinema.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {

        if (username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUsernameOrPasswordException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
