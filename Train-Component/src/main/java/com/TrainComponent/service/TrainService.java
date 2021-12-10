package com.TrainComponent.service;

import com.TrainComponent.model.Train;

import java.util.List;

public interface TrainService {

    public Train getTrainByNo(String trainNo);

    public List<Train> getAllTrain();

    public Train addTrain(Train train);

    public Train updateTrain(Train train);

    public String deleteByTrainNo(String trainNo);

    public Train getTrainByTrainName(String trainName);

    public List<Train> getTrainBySourceAndDestination(String source , String destination);

}
