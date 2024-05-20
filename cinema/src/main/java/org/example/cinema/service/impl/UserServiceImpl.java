package org.example.cinema.service.impl;


import org.example.cinema.model.User;
import org.example.cinema.model.UserType;
import org.example.cinema.model.exceptions.InvalidUserIdException;
import org.example.cinema.model.exceptions.InvalidUsernameOrPasswordException;
import org.example.cinema.model.exceptions.PasswordsDoNotMatchException;
import org.example.cinema.model.exceptions.UsernameAlreadyExistsException;
import org.example.cinema.repository.UserRepository;
import org.example.cinema.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(InvalidUserIdException::new);
    }

    @Override
    public User create(String username, String password, String repeatedPassword, String name, String surname, UserType userType) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }

        if (!password.equals(repeatedPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(
                username,
                name,
                surname,
                passwordEncoder.encode(password),
                userType
        );

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
