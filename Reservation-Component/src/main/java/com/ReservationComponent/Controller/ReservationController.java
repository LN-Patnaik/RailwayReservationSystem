package com.ReservationComponent.Controller;

import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    TicketService ticketService;

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

    @GetMapping
    public String cancelTicket(@PathVariable String pnrNum)
    {
        ticketService.cancelTicket(pnrNum);
        return "ticket status with pnrNum"+pnrNum;
    }

}
