package org.example.cinema.web;

import org.example.cinema.model.Movie;
import org.example.cinema.service.MovieService;
import org.example.cinema.service.ProductionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
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

    @GetMapping("/movies/add")
    public String showAdd(Model model) {
        model.addAttribute("productions", productionService.findAll());
        return "form";
    }

    @GetMapping("/movies/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("movie", this.movieService.findById(id));
        model.addAttribute("productions", productionService.findAll());
        return "form";
    }

    @PostMapping("/movies")
    public String create(@RequestParam String title,
                         @RequestParam String summary,
                         @RequestParam Double rating,
                         @RequestParam Long production) {
        this.movieService.create(title, summary, rating, production);
        return "redirect:/movies";
    }

    @PostMapping("/movies/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String summary,
                         @RequestParam Double rating,
                         @RequestParam Long production) {
        this.movieService.update(id, title, summary, rating, production);
        return "redirect:/movies";
    }

    @PostMapping("/movies/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.movieService.delete(id);
        return "redirect:/movies";
    }
}
