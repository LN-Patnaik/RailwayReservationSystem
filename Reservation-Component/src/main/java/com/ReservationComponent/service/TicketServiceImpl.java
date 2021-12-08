package com.ReservationComponent.service;

import com.ReservationComponent.model.Passenger;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.repository.TicketRepository;
import com.ReservationComponent.utils.ReservationConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket getTicketByPnrNum(String pnrNum) {
        Ticket ticket = ticketRepository.findById(pnrNum).get();
        return ticket;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
       Ticket addedTicket = ticketRepository.save(ticket);
       return addedTicket;
    }

    @Override
    public String cancelTicket(String pnrNum) {
        if(StringUtils.isNotBlank(pnrNum)){
            Ticket ticket = ticketRepository.findById(pnrNum).get();
            if(Objects.nonNull(ticket))
            {
                if(!ticket.getStatus().equals(ReservationConstants.CANCEL)){
                    ticket.setStatus(ReservationConstants.CANCEL);
                    ticketRepository.save(ticket);
                    return "Ticket Cancelled Successfully";
                }
                else{
                    return "The Provided PNR Number is already CANCELLED";
                }

            }
            else
            {
                return "The Provided PNR Number doesn't exist in the system";
            }
        }
        else{
            return "PNR Number is null Please provide a valid PNR Number";
        }
    }


}
