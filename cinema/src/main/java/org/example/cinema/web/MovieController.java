package org.example.cinema.web;

import org.example.cinema.model.Movie;
import org.example.cinema.model.Production;
import org.example.cinema.model.UserType;
import org.example.cinema.service.MovieService;
import org.example.cinema.service.ProductionService;
import org.example.cinema.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }


    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Movie> movies = this.movieService.listAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("bodyContent", "list");
        return "master";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addMovie(Model model) {
        List<Production> productions = this.productionService.findAll();
        model.addAttribute("productions", productions);
        model.addAttribute("bodyContent", "form");
        return "master";
    }

    @GetMapping("/movies/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("movie", this.movieService.findById(id));
        model.addAttribute("productions", productionService.findAll());
        return "form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String saveMovie(@RequestParam String title,
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
