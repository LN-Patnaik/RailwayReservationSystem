package com.ReservationComponent.service;

import com.ReservationComponent.ReservationException;
import com.ReservationComponent.model.Ticket;
import com.ReservationComponent.model.Train;
import com.ReservationComponent.model.Wallet;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WalletService walletService;

    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    /**
     * Method to get ticket by PNR Number.
     *
     * @param pnrNum
     * @return
     */

    @Override
    public Ticket getTicketByPnrNum(long pnrNum) {
        logger.info(">> getTicketByPnrNum");
        Ticket ticket = ticketRepository.findById(pnrNum).get();
        logger.info("<< getTicketByPnrNum");
        return ticket;
    }

    /**
     * Method to create ticket
     *
     * @param ticket
     * @return
     */
    @Override
    @Transactional
    public Ticket addTicket(Ticket ticket) throws ReservationException {
        logger.trace(">> addTicket()");
        Ticket addedTicket = null;
        Train train = getTrainByTrainNumber(ticket.getTrainNumber());
        if (Objects.nonNull(train) && train.getSeats_Available() > 0 && ticket.getPassengerDetails().size() <= train.getSeats_Available()) {
            int availableSeats = train.getSeats_Available() - ticket.getPassengerDetails().size();
            train.setSeats_Available(availableSeats);
            int totalFare= train.getFare()*ticket.getPassengerDetails().size();
            ticket.setFare(BigDecimal.valueOf(totalFare));
            Wallet wallet = walletService.getWalletByUserId(ticket.getUserId());
            if(Objects.nonNull(wallet)){
                if(wallet.getBalance() < totalFare){
                    throw new ReservationException("Wallet Balance is insufficient!! Please add and proceed");
                }
                else{
                    int balance = wallet.getBalance() - totalFare;
                    wallet.setBalance(balance);
                    walletService.updateWallet(wallet);
                }
            }
            Train updatedTrain = updateTrainAvailableSeats(train);
            ticket.setStatus("CONFIRMED");
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
    public Train updateTrainAvailableSeats(Train train) {
        logger.trace(">> updateTrainAvailableSeats()");
        if(Objects.nonNull(train)){
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<Train> entity = new HttpEntity<Train>(train, headers);
            logger.trace("<< updateTrainAvailableSeats()");
            return restTemplate.exchange(
                    "http://localhost:8080/train/updateTrain", HttpMethod.PUT, entity, Train.class).getBody();
        }
        return null;
    }

    /**
     * Method to get train details by train number from train component
     *
     * @param trainNumber
     * @return
     */
    public Train getTrainByTrainNumber(String trainNumber) {
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
    @Transactional
    public String cancelTicket(String pnrNum) {
        logger.trace(">> cancelTicket()");
        if (StringUtils.isNotBlank(pnrNum)) {
            Optional<Ticket> ticketOpt = ticketRepository.findById(Long.valueOf(pnrNum));
            if (ticketOpt.isPresent()) {
                Ticket ticket = ticketOpt.get();
                if (Objects.nonNull(ticket)) {
                    if (!ticket.getStatus().equals(ReservationConstants.CANCEL)) {
                        ticket.setStatus(ReservationConstants.CANCEL);
                        ticketRepository.save(ticket);
                        Wallet wallet = walletService.getWalletByUserId(ticket.getUserId());
                        wallet.setBalance(wallet.getBalance() + ticket.getFare().intValue());
                        walletService.updateWallet(wallet);

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

    @Override
    public List<Ticket> getTicketByUserId(String userId) {
        return ticketRepository.getTicketByUserId(userId);
        //return ticketRepository.findAll().stream().filter((t->(t.getUserId().equals(userId))))
        //        .collect(Collectors.toList());
    }

}
