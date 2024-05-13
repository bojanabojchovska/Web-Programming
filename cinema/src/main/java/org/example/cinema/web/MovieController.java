package org.example.cinema.web;

import org.example.cinema.model.Movie;
import org.example.cinema.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping({"/", "/movies"})
    public String showMovies(@RequestParam(required = false) String movieName,
                             @RequestParam(required = false) Double rating,
                             Model model) {
        List<Movie> movies;
        if (movieName == null && rating == null) {
            movies = movieService.listAllMovies();
        } else {
            movies = this.movieService.listMoviesByTitleAndRating(movieName,rating);
        }
        model.addAttribute("movies", movies);

        return "list";
    }

    @PostMapping("/movies/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.movieService.delete(id);
        return "redirect:/movies";
    }
}
