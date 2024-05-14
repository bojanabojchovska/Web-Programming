package org.example.cinema.web;


import org.example.cinema.model.Movie;
import org.example.cinema.model.TicketOrder;
import org.example.cinema.model.User;
import org.example.cinema.service.MovieService;
import org.example.cinema.service.TicketOrderService;
import org.example.cinema.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;
    private final UserService userService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService, UserService userService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping("/ticketOrder")
    public String showTicketOrder(@RequestParam Integer numTickets,
                                 @RequestParam Long selectedMovie, Model model) {

        Movie movie = movieService.findById(selectedMovie);
        ticketOrderService.placeOrder(movie.getTitle(),userService.listAllUsers().getFirst().getId(),numTickets);

        model.addAttribute("movie", movie);
        model.addAttribute("numTickets", numTickets);

        return "ticket_order";
    }
}
