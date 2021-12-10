package com.ReservationComponent.Controller;

import com.ReservationComponent.model.Passenger;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.repository.PassengerRepository;
import com.ReservationComponent.repository.TicketRepository;
import com.ReservationComponent.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@Api(value="/reservation",tags = "Booking Management")
public class ReservationController {
    @Autowired
    TicketService ticketService;

    @Autowired
    PassengerRepository passengerRepository;

    @RequestMapping("/hello")
    public String displayHelloWorld() {
        return "Hello World";
    }


    @GetMapping("/ticket/{pnrNum}")
    @ApiOperation(value = "Get ticket by pnr number",tags={"Booking Management"})
    @ApiResponses(value = {
            @ApiResponse(code=200,message = "Ticket data"),
            @ApiResponse(code=404,message = "Invalid data"),
            @ApiResponse(code=200,message = "Internal Server error")})
    public Ticket getTicket(@PathVariable String pnrNum) {
        Ticket ticket = ticketService.getTicketByPnrNum(pnrNum);
        return ticket;
    }

    @PostMapping("/addTicket")
    @ApiOperation(value = "Create Ticket",tags={"Booking Management"})
    @ApiResponses(value = {
            @ApiResponse(code=200,message = "Ticket Created Successfully"),
            @ApiResponse(code=404,message = "Invalid data"),
            @ApiResponse(code=200,message = "Internal Server error")})
    public String addTicket(@RequestBody Ticket ticket) {
        Ticket addedTicket = ticketService.addTicket(ticket);
        return "Ticket Created Successfully:- " + addedTicket;
    }

    @GetMapping("/cancelTicket/{pnrNum}")
    public String cancelTicket(@PathVariable String pnrNum) {
        String response = ticketService.cancelTicket(pnrNum);
        return response;
    }

    @PostMapping("/addPassenger")
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        Passenger addedPassenger = passengerRepository.save(passenger);
        return addedPassenger;
    }

}
