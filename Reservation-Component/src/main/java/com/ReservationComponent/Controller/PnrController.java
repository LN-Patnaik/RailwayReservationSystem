package com.ReservationComponent.Controller;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation/pnr")
public class PnrController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket/{pnrNum}")
    @Operation(summary = "This is to get ticket by pnr number")
    public Ticket getTicket(@PathVariable long pnrNum)
    {
        Ticket ticket=ticketService.getTicketByPnrNum(pnrNum);
        return ticket;
    }
}
