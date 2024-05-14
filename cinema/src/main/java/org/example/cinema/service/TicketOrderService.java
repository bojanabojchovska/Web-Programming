package org.example.cinema.service;

import org.example.cinema.model.Movie;
import org.example.cinema.model.TicketOrder;
import org.example.cinema.model.User;

import java.util.List;

public interface TicketOrderService {

    TicketOrder placeOrder(String movieTitle, Long user, int numberOfTickets);

    List<TicketOrder> listAllTicketOrders();


    TicketOrder findById(Long id);

    TicketOrder delete(Long id);
}
