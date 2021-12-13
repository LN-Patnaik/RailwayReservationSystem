package com.ReservationComponent.Controller;

import com.ReservationComponent.model.Passenger;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.repository.PassengerRepository;
import com.ReservationComponent.repository.TicketRepository;
import com.ReservationComponent.service.SequenceGeneratorService;
import com.ReservationComponent.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/hello")
    public String displayHelloWorld() {
        return "Hello World";
    }


    @GetMapping("/ticket/{pnrNum}")
    @Operation(summary = "This is to get ticket by pnr number")
    public Ticket getTicket(@PathVariable String pnrNum) {
        Ticket ticket = ticketService.getTicketByPnrNum(pnrNum);
        return ticket;
    }

    @PostMapping("/addTicket")
    @Operation(summary = "This is to create ticket")
    public String addTicket(@RequestBody Ticket ticket) {
        ticket.setPnrNumber(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME));
        Ticket addedTicket = ticketService.addTicket(ticket);
        return "Ticket Created Successfully:- " + addedTicket;
    }


    @GetMapping("/cancelTicket/{pnrNum}")
    @Operation(summary = "This is to cancel ticket")
    public String cancelTicket(@PathVariable String pnrNum) {
        String response = ticketService.cancelTicket(pnrNum);
        return response;
    }



}
