package com.ReservationComponent.repository;

import com.ReservationComponent.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, Long> {
     List<Ticket> getTicketByUserId(String userId);
}
