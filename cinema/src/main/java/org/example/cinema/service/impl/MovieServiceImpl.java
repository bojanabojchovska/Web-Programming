package org.example.cinema.service.impl;

import org.example.cinema.model.Movie;
import org.example.cinema.model.Production;
import org.example.cinema.model.exceptions.InvalidMovieIdException;
import org.example.cinema.model.exceptions.InvalidProductionIdException;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.repository.ProductionRepository;
import org.example.cinema.service.MovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return null;
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Movie create(String title, String summary, double rating, Long prodId) {
        Production production = productionRepository.findById(prodId).orElseThrow(InvalidProductionIdException::new);
        return movieRepository.save(new Movie(title,summary,rating, production));
    }

    @Override
    public Movie update(Long id, String title, String summary, double rating, Long prodId) {
        Movie movie = this.findById(id);

        movie.setTitle(title);
        movie.setSummary(summary);
        movie.setRating(rating);

        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movie = this.findById(id);
        movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    @Override
    public List<Movie> listMoviesByTitleAndRating(String movieName, Double rating) {
        if(movieName!=null && rating!=null){
            return movieRepository.findAllByTitleContainingIgnoreCaseAndRatingGreaterThan(movieName,rating);
        }
        else if(movieName!=null){
            return movieRepository.findAllByTitleContainingIgnoreCase(movieName);
        }
        else if (rating!=null){
            return movieRepository.findAllByRatingGreaterThan(rating);
        }
        return movieRepository.findAll();
    }
}
