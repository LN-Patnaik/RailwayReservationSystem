package com.ReservationComponent.Controller;

import com.ReservationComponent.model.Passenger;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.repository.PassengerRepository;
import com.ReservationComponent.repository.TicketRepository;
import com.ReservationComponent.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    TicketService ticketService;

    @Autowired
    PassengerRepository passengerRepository;

    @RequestMapping("/hello")
    public String displayHelloWorld()
    {
        return "Hello World";
    }

    @GetMapping("/ticket/{pnrNum}")
    public Ticket getTicket(@PathVariable String pnrNum)
    {
        Ticket ticket=ticketService.getTicketByPnrNum(pnrNum);
        return ticket;
    }
    @PostMapping("/addTicket")
    public Ticket addTicket(@RequestBody Ticket ticket)
    {
        Ticket addedTicket = ticketService.addTicket(ticket);
        return addedTicket;
    }

    @GetMapping("/cancelTicket/{pnrNum}")
    public String cancelTicket(@PathVariable String pnrNum)
    {
        String response = ticketService.cancelTicket(pnrNum);
        return response;
    }

    @PostMapping("/addPassenger")
    public Passenger addPassenger(@RequestBody Passenger passenger)
    {
        Passenger addedPassenger=passengerRepository.save(passenger);
        return addedPassenger;
    }

}
