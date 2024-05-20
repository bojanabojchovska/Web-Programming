package org.example.cinema.model.exceptions;

public class MovieAlreadyInTicketCartException extends RuntimeException{

    public MovieAlreadyInTicketCartException(Long id, String username) {
        super(String.format("Movie with id: %d already exists in ticket cart for user with username %s", id, username));
    }
}
