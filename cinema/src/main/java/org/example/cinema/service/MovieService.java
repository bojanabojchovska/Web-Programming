package org.example.cinema.service;

import org.example.cinema.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {

    List<Movie> listAllMovies();
    List<Movie> searchMovies(String text);

    Movie findById(Long id);

    Movie create(String title, String summary, Double rating, Long prodId);
    Movie update(Long id, String title, String summary, Double rating, Long prodId);
    Movie delete(Long id);

    Movie findByTitle(String title);

    List<Movie> listMoviesByTitleAndRating(String movieName, Double rating);


}
