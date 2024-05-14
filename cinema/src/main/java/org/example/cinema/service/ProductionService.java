package org.example.cinema.service;

import org.example.cinema.model.Movie;
import org.example.cinema.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {

    List<Production> findAll();

    Optional<Production> findById(Long id);

    Production create(String name, String country, String address);
}
