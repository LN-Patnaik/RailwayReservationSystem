package com.ReservationComponent.service;

import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.model.Train;
import com.ReservationComponent.repository.TicketRepository;
import com.ReservationComponent.utils.ReservationConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    /**
     * Method to get ticket by PNR Number.
     *
     * @param pnrNum
     * @return
     */
    @Override
    public Ticket getTicketByPnrNum(String pnrNum) {
        logger.trace(">> getTicketByPnrNum");
        Ticket ticket = ticketRepository.findById(pnrNum).get();
        logger.trace("<< getTicketByPnrNum");
        return ticket;
    }

    /**
     * Method to create ticket
     *
     * @param ticket
     * @return
     */
    @Override
    public Ticket addTicket(Ticket ticket) {
        logger.trace(">> addTicket()");
        Ticket addedTicket = null;
        Train train = getTrainByTrainNumber(ticket.getTrainNumber());
        if (Objects.nonNull(train) && train.getSeats_Available() > 0 && ticket.getPassengerDetails().size() <= train.getSeats_Available()) {
            int availableSeats = train.getSeats_Available() - ticket.getPassengerDetails().size();
            train.setSeats_Available(availableSeats);
            int totalFare= train.getFare()*ticket.getPassengerDetails().size();
            ticket.setFare(BigDecimal.valueOf(totalFare));
            Train updatedTrain = updateTrainAvailableSeats(train);
            addedTicket = ticketRepository.save(ticket);
            logger.info("Updated train Details:-" + updatedTrain);
        }
        logger.trace("<< addTicket()");
        return addedTicket;
    }

    /**
     * Method to update Train available seats post creation and cancellation of tickets
     *
     * @param train
     */
    private Train updateTrainAvailableSeats(Train train) {
        logger.trace(">> updateTrainAvailableSeats()");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Train> entity = new HttpEntity<Train>(train, headers);
        logger.trace("<< updateTrainAvailableSeats()");
        return restTemplate.exchange(
                "http://localhost:8080/train/updateTrain", HttpMethod.PUT, entity, Train.class).getBody();
    }

    /**
     * Method to get train details by train number from train component
     *
     * @param trainNumber
     * @return
     */
    private Train getTrainByTrainNumber(String trainNumber) {
        logger.trace(">> getTrainByTrainNumber()");
        if (StringUtils.isNotBlank(trainNumber)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Train> entity = new HttpEntity<Train>(headers);
            logger.trace("<< getTrainByTrainNumber()");
            return restTemplate.exchange(
                    "http://localhost:8080/train/trains/" + trainNumber, HttpMethod.GET, entity, Train.class).getBody();

        } else {
            logger.trace("<< getTrainByTrainNumber()");
            return null;
        }
    }

    /**
     * Method to cancel train ticket
     *
     * @param pnrNum
     * @return
     */
    @Override
    public String cancelTicket(String pnrNum) {
        logger.trace(">> cancelTicket()");
        if (StringUtils.isNotBlank(pnrNum)) {
            Optional<Ticket> ticketOpt = ticketRepository.findById(pnrNum);
            if (ticketOpt.isPresent()) {
                Ticket ticket = ticketOpt.get();
                if (Objects.nonNull(ticket)) {
                    if (!ticket.getStatus().equals(ReservationConstants.CANCEL)) {
                        ticket.setStatus(ReservationConstants.CANCEL);
                        ticketRepository.save(ticket);
                        Train train = getTrainByTrainNumber(ticket.getTrainNumber());
                        if (Objects.nonNull(train)) {
                            int availableSeats = train.getSeats_Available() + ticket.getPassengerDetails().size();
                            train.setSeats_Available(availableSeats);
                            updateTrainAvailableSeats(train);
                            logger.trace("<< cancelTicket()");
                            return "Ticket Cancelled Successfully";
                        } else {
                            logger.trace("<< cancelTicket()");
                            return "Couldn't fetch train from train component";
                        }

                    } else {
                        logger.trace("<< cancelTicket()");
                        return "The Provided PNR Number is already CANCELLED";
                    }

                } else {
                    logger.trace("<< cancelTicket()");
                    return "The Provided PNR Number doesn't exist in the system";
                }
            } else {
                logger.trace("<< cancelTicket()");
                return "Ticket details couldn't be fetched, Please Check!";
            }

        } else {
            logger.trace("<< cancelTicket()");
            return "PNR Number is null Please provide a valid PNR Number";
        }
    }

}
