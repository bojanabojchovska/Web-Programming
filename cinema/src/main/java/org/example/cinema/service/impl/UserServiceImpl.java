package org.example.cinema.service.impl;


import org.example.cinema.model.ShoppingCart;
import org.example.cinema.model.User;
import org.example.cinema.model.exceptions.InvalidUserIdException;
import org.example.cinema.repository.UserRepository;
import org.example.cinema.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public User create(String username, String name, String surname, String password, LocalDate dateOfBirth) {
        return userRepository.save(new User(username,name,surname,password,dateOfBirth));
    }

    @Override
    public User update(Long id, String username, String name, String surname, String password, LocalDate dateOfBirth, List<ShoppingCart> carts) {
        User user = this.findById(id);

        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setDateOfBirth(dateOfBirth);
        user.setCarts(carts);

        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
        return user;
    }
}
