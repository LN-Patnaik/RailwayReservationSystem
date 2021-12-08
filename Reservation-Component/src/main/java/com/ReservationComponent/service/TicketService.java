package com.ReservationComponent.service;

import com.ReservationComponent.model.Ticket;

import java.util.List;

public interface TicketService {

    public Ticket getTicketByPnrNum(String pnrNum);

    public Ticket addTicket(Ticket ticket);

    public String cancelTicket(String pnrNum);


}
