package org.example.cinema.service.impl;


import org.example.cinema.model.Movie;
import org.example.cinema.model.TicketOrder;
import org.example.cinema.model.User;
import org.example.cinema.model.exceptions.InvalidTicketOrderIdException;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.repository.TicketOrderRepository;
import org.example.cinema.repository.UserRepository;
import org.example.cinema.service.TicketOrderService;
import org.example.cinema.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;
    private final MovieRepository movieRepository;

    private final UserService userService;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository, MovieRepository movieRepository, UserService userService) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    @Override
    public TicketOrder placeOrder(String movieTitle, Long user, int numberOfTickets) {
        Movie movie = movieRepository.findMovieByTitle(movieTitle);
        User user1 = userService.findById(user);
        return ticketOrderRepository.save(new TicketOrder(movie,user1,numberOfTickets));
    }

    @Override
    public List<TicketOrder> listAllTicketOrders() {
        return ticketOrderRepository.findAll();
    }

    @Override
    public TicketOrder findById(Long id) {
        return ticketOrderRepository.findById(id).orElseThrow(InvalidTicketOrderIdException::new);
    }

    @Override
    public TicketOrder delete(Long id) {
        TicketOrder ticketOrder = this.findById(id);
        ticketOrderRepository.delete(ticketOrder);
        return ticketOrder;
    }
}
