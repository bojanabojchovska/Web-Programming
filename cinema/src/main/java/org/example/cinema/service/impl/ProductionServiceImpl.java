package org.example.cinema.service.impl;

import org.example.cinema.model.Movie;
import org.example.cinema.model.Production;
import org.example.cinema.repository.ProductionRepository;
import org.example.cinema.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Production> findAll() {
        return productionRepository.findAll();
    }

    @Override
    public Optional<Production> findById(Long id) {
        return productionRepository.findById(id);
    }

    @Override
    public Production create(String name, String country, String address) {
        return productionRepository.save(new Production(name, country, address));
    }
}
