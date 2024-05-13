package org.example.cinema.config;

import jakarta.annotation.PostConstruct;

import org.example.cinema.model.Movie;
import org.example.cinema.model.Production;
import org.example.cinema.service.MovieService;
import org.example.cinema.service.ProductionService;
import org.example.cinema.service.TicketOrderService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {

    private final MovieService movieService;
    private final ProductionService productionService;


    public DataInitializer(MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }


    @PostConstruct
    public void initData() {

        this.productionService.create("Vardar Film", "Macedonia", "Skopje");

        Production production = productionService.findAll().getFirst();

        this.movieService.create("Challengers", "Tashi, a former tennis prodigy turned coach, turned her husband into a champion. But to overcome a losing streak, he needs to face his ex-best friend and Tashi's ex-boyfriend.", 7.8, production.getId());
        this.movieService.create("The Fall Guy", "A down-and-out stuntman must find the missing star of his ex-girlfriend's blockbuster film.", 7.3, production.getId());
        this.movieService.create("The Idea of You", "Solène, a 40-year-old single mom, begins an unexpected romance with 24-year-old Hayes Campbell, the lead singer of August Moon, the hottest boy band on the planet.", 6.4, production.getId());

    }
}
