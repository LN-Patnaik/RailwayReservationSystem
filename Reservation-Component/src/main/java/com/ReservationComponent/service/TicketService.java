package com.ReservationComponent.service;

import com.ReservationComponent.ReservationException;
import com.ReservationComponent.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    public Ticket getTicketByPnrNum(long pnrNum);

    public Ticket addTicket(Ticket ticket) throws ReservationException;

    public String cancelTicket(String pnrNum);

    public List<Ticket> getTicketByUserId(String userId);

}
