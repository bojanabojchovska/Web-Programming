package org.example.cinema.service;

import org.example.cinema.model.Movie;
import org.example.cinema.model.ShoppingCart;
import org.example.cinema.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> listAllUsers();
    User findById(Long id);

    User create(String username, String name, String surname, String password, LocalDate dateOfBirth);

    User update(Long id, String username, String name, String surname, String password, LocalDate dateOfBirth, List<ShoppingCart> carts);

    User delete(Long id);

}
