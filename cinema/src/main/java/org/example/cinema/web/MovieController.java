package org.example.cinema.web;

import org.example.cinema.model.Movie;
import org.example.cinema.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping({"/", "/movies"})
    public String showCandidates(@RequestParam(required = false) Integer years,
                                 Model model) {
        List<Movie> movies;
//        if (years == null && gender == null) {
        movies = movieService.listAllMovies();
//        } else {
//            candidateList = this.candidateService.listCandidatesYearsMoreThanAndGender(years, gender);
//        }
        model.addAttribute("movies", movies);

        return "list";
    }
}
