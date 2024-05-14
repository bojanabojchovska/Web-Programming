package org.example.cinema.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class TicketOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Movie movie;


    @ManyToOne
    private User user;
    private Integer numberOfTickets;

    public TicketOrder() {
    }

    public TicketOrder(Movie movie, User user, int numberOfTickets) {
        this.movie = movie;
        this.numberOfTickets = numberOfTickets;
    }
}
