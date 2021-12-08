package com.ReservationComponent.repository;

import com.ReservationComponent.model.Passenger;
import com.ReservationComponent.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
}
