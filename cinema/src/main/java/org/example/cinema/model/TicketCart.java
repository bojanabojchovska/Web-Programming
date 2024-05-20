package org.example.cinema.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class TicketCart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name = "movies_ticket_order")
    private List<Movie> movies;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;


    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TicketOrderStatus status;

    public TicketCart() {
    }

    public TicketCart(User user) {
        this.movies = new ArrayList<>();
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.status = TicketOrderStatus.CREATED;
    }
}
