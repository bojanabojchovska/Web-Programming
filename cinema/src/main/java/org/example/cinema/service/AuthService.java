package org.example.cinema.service;

import org.example.cinema.model.User;

import java.util.List;

public interface AuthService {

    User login(String username, String password);

    List<User> findAll();
}
