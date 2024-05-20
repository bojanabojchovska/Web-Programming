package org.example.cinema.service;

import org.example.cinema.model.Movie;
import org.example.cinema.model.TicketCart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketOrderService {

    TicketCart placeOrder(String username, Long movieId);

    List<Movie> listAllMoviesInTicketCart(Long cartId);

    TicketCart getActiveTicketCart(String username);

    TicketCart addMovieToTicketCart(String username, Long movieId);

    TicketCart deleteMovieFromTicketCart(String username, Long movieId);

    List<TicketCart> findAll();

    Long countSuccessfulOrders(String username);

    TicketCart save(TicketCart ticketCart);
    TicketCart findById(Long id);


}
