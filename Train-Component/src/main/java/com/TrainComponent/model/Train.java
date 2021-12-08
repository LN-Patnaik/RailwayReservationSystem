package com.TrainComponent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Train {

    @Id
    private String trainNo;
    private String trainName;
    private String source;
    private String destination;
    private List<List<String>> Seats;

    public List<List<String>> getSeats() {
        return Seats;
    }

    public void setSeats(List<List<String>> seats) {
        Seats = seats;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
