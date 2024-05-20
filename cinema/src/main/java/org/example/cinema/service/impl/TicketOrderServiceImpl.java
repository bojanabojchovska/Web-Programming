package org.example.cinema.service.impl;


import org.example.cinema.model.Movie;
import org.example.cinema.model.TicketCart;
import org.example.cinema.model.TicketOrderStatus;
import org.example.cinema.model.User;
import org.example.cinema.model.exceptions.InvalidMovieIdException;
import org.example.cinema.model.exceptions.InvalidTicketCartIdException;
import org.example.cinema.model.exceptions.MovieAlreadyInTicketCartException;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.repository.TicketCartRepository;
import org.example.cinema.service.TicketOrderService;
import org.example.cinema.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketCartRepository ticketCartRepository;
    private final MovieRepository movieRepository;

    private final UserService userService;

    public TicketOrderServiceImpl(TicketCartRepository ticketCartRepository, MovieRepository movieRepository, UserService userService) {
        this.ticketCartRepository = ticketCartRepository;
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    @Override
    public TicketCart placeOrder(String username, Long movieId) {
        return null;
    }

    @Override
    public List<Movie> listAllMoviesInTicketCart(Long cartId) {
        TicketCart ticketCart = this.findById(cartId);
        return ticketCart.getMovies();
    }

    @Override
    public TicketCart getActiveTicketCart(String username) {
        return this.ticketCartRepository
                .findTicketCartByUserUsernameAndStatus(username, TicketOrderStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userService.findByUsername(username);
                    TicketCart ticketCart = new TicketCart(user);
                    return this.ticketCartRepository.save(ticketCart);
                });
    }

    @Override
    public TicketCart addMovieToTicketCart(String username, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(InvalidMovieIdException::new);
        TicketCart ticketCart = this.getActiveTicketCart(username);

        List<Movie> moviesInTicketCart = ticketCart.getMovies().stream()
                .filter(i -> i.getId().equals(movieId))
                .collect(Collectors.toList());

        if (moviesInTicketCart.size()>0){
            throw new MovieAlreadyInTicketCartException(movieId,username);
        }

        ticketCart.getMovies().add(movie);
        return this.ticketCartRepository.save(ticketCart);
    }

    @Override
    public TicketCart deleteMovieFromTicketCart(String username, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(InvalidMovieIdException::new);
        TicketCart ticketCart = this.getActiveTicketCart(username);
        ticketCart.getMovies().remove(movie);
        return this.ticketCartRepository.save(ticketCart);
    }

    @Override
    public List<TicketCart> findAll() {
        return this.ticketCartRepository.findAll();
    }

    @Override
    public Long countSuccessfulOrders(String username) {
        return null;
    }

    @Override
    public TicketCart save(TicketCart ticketCart) {
        return this.ticketCartRepository.save(ticketCart);
    }

    @Override
    public TicketCart findById(Long id) {
        return ticketCartRepository.findById(id).orElseThrow(InvalidTicketCartIdException::new);
    }
}

