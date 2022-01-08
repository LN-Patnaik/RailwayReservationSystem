package com.ReservationComponent.Controller;

import com.ReservationComponent.ReservationException;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.model.Wallet;
import com.ReservationComponent.service.SequenceGeneratorService;
import com.ReservationComponent.service.TicketService;
import com.ReservationComponent.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/reservation/user")
public class ReservationController {
    @Autowired
    TicketService ticketService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    WalletService walletService;

    @GetMapping("/hello")
    public String displayHelloWorld() {
        return "Hello World";
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/ticket/{pnrNum}")
//    @Operation(summary = "This is to get ticket by pnr number")
//    public Ticket getTicket(@PathVariable long pnrNum) {
//        Ticket ticket = ticketService.getTicketByPnrNum(pnrNum);
//        return ticket;
//    }


    @PostMapping("/addTicket")
    @Operation(summary = "This is to create ticket")
    public Ticket addTicket(@RequestBody Ticket ticket) throws ReservationException {
        ticket.setPnrNumber(sequenceGeneratorService.generateSequence(Ticket.SEQUENCE_NAME));
        Ticket addedTicket = ticketService.addTicket(ticket);
        return  addedTicket;
    }


    @GetMapping("/cancelTicket/{pnrNum}")
    @Operation(summary = "This is to cancel ticket")
    public String cancelTicket(@PathVariable String pnrNum) {
        String response = ticketService.cancelTicket(pnrNum);
        return response;
    }

    @GetMapping("/walletDetails/{walletId}")
    @Operation(summary = "This is to get wallet details by walletId")
    public Wallet getWalletDetailsByWalletId(@PathVariable long walletId)
    {
        Wallet wallet=walletService.getWalletByWalletId(walletId);
        return wallet;
    }

    @PostMapping("/createWallet")
    @Operation(summary = "This is to create wallet")
    public Wallet createWallet(@RequestBody Wallet wallet)
    {
        Wallet addedWallet=walletService.createWallet(wallet);
        return addedWallet;
    }

    @PostMapping("/updateWallet")
    @Operation(summary = "This is to update wallet")
    public Wallet updateWallet(@RequestBody Wallet wallet)
    {
        Wallet updatedWallet=walletService.updateWallet(wallet);
        if(Objects.nonNull(updatedWallet)){
            return updatedWallet;
        }
        return null;
    }

    @PostMapping("/addBalance")
    @Operation(summary = "This is to add balance to wallet")
    public String updateWalletBalanceByWalletId(@QueryParam("walletId") Long walletId, @QueryParam("amount") double amount)
    {
        Wallet updatedWallet=walletService.updateBalance(walletId, amount);
        if(Objects.nonNull(updatedWallet)){
            return "Amount added successfully";
        }
        return "Adding Amount Failed!! Please Retry!!";
    }
    @GetMapping("/ticket/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable String userId)
    {
        List<Ticket> userTickets=ticketService.getTicketByUserId(userId);
        return userTickets;
    }

}
