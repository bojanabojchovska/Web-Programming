package org.example.cinema.repository;

import org.example.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByTitleContainingIgnoreCaseAndRatingGreaterThan(String movieName, Double rating);
    List<Movie> findAllByTitleContainingIgnoreCase(String movieName);
    List<Movie> findAllByRatingGreaterThan(Double rating);

    Movie findMovieByTitle(String text);
}
