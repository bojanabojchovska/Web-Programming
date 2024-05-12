package org.example.cinema.service.impl;

import org.example.cinema.model.Movie;
import org.example.cinema.model.exceptions.InvalidMovieIdException;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
    public Movie create(String title, String summary, double rating) {
        return movieRepository.save(new Movie(title,summary,rating));
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
}
