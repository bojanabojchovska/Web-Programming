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

    private String clientName;
    private String clientAddress;
    private Integer numberOfTickets;

    public TicketOrder() {
    }

    public TicketOrder(Movie movie, String clientName, String clientAddress, int numberOfTickets) {
        this.movie = movie;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
