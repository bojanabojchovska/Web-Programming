package org.example.cinema.repository;

import org.example.cinema.model.TicketCart;
import org.example.cinema.model.TicketOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketCartRepository extends JpaRepository<TicketCart, Long> {

    Optional<TicketCart> findTicketCartByUserUsernameAndStatus(String username, TicketOrderStatus status);

}
