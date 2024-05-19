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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;
    private final UserService userService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService, UserService userService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
        this.userService = userService;
    }

//    @GetMapping("{id}")
//    public String showTicketOrder(@PathVariable Long id,
//                                  Model model) {
//
//        Movie movie = movieService.findById(id);
//        ticketOrderService.placeOrder(movie.getTitle(),);
//        model.addAttribute("movie", movie);
//        return "ticket_order";
//    }
}
