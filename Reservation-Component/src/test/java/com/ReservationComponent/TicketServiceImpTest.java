package com.ReservationComponent;

import com.ReservationComponent.model.Passenger;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.model.Train;
import com.ReservationComponent.repository.TicketRepository;
import com.ReservationComponent.service.TicketServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImpTest {


    @InjectMocks
    TicketServiceImpl ticketService;
    @Mock
    TicketRepository ticketRepository;

    @Before
    public void setup()
    {

    }

    @Test
    public void testGetTicketByPnrNum()
    {
        long pnrNum  = 123;
        Ticket ticket = new Ticket();
        ticket.setPnrNumber(pnrNum);
        Mockito.when(ticketRepository.findById(Long.valueOf(String.valueOf(pnrNum)))).thenReturn(Optional.of(ticket));
        Ticket fetchedPnr = ticketService.getTicketByPnrNum(Long.parseLong(String.valueOf(pnrNum)));
        Assert.assertEquals(pnrNum, fetchedPnr.getPnrNumber());
    }

}
