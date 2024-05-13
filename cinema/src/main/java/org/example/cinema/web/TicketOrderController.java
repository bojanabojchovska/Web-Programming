package org.example.cinema.web;


import org.example.cinema.model.Movie;
import org.example.cinema.model.TicketOrder;
import org.example.cinema.service.MovieService;
import org.example.cinema.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
    }

    @GetMapping("/ticketOrder")
    public String showTicketOrder(@RequestParam Integer numTickets,
                                 @RequestParam Long selectedMovie, Model model) {

        Movie movie = movieService.findById(selectedMovie);
        ticketOrderService.placeOrder(movie.getTitle(), "Bojana Bojchovska", "Maygasse", numTickets);

        model.addAttribute("movie", movie);
        model.addAttribute("numTickets", numTickets);

        return "ticket_order";
    }
}
