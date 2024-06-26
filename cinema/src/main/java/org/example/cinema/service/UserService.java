package org.example.cinema.service;

import org.example.cinema.model.Movie;
import org.example.cinema.model.User;
import org.example.cinema.model.UserType;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> listAllUsers();
    User findById(Long id);

    User create(String username, String password, String repeatedPassword, String name, String surname, UserType userType);

    User findByUsername(String username);

}
