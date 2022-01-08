package com.TrainComponent.service;

import com.TrainComponent.model.Train;
import com.TrainComponent.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements  TrainService{

    @Autowired
    TrainRepository trainRepository;


    /**
     * Get Train by train no.
     *
     * @param trainNo
     * @return
     */

    @Override
    public Train getTrainByNo(String trainNo) {
        Optional trainOpt = trainRepository.findById(trainNo);
        if(trainOpt.isPresent()){
            Train train = (Train) trainOpt.get();
            return  train;
        }
        return null;
    }

    /**
     * Method to get all train
     *
     * @return
     */

    @Override
    public List<Train> getAllTrain() {
        List<Train> trains=trainRepository.findAll();
        return trains;
    }

    @Override
    public Train addTrain(Train train) {
        Train addedTrain=trainRepository.save(train);
        return addedTrain;
    }

    @Override
    public Train updateTrain(Train train) {
        Train updatedTrain= trainRepository.save(train);
        return updatedTrain;
    }

    @Override
    public String deleteByTrainNo(String trainNo) {
        trainRepository.deleteById(trainNo);
        return "Deleted Successfully";
    }

    /**
     *
     * @param trainName
     * @return
     */
    @Override
    public Train getTrainByTrainName(String trainName) {
        Train train = trainRepository.getTrainByTrainName(trainName);
        return train;
    }

    /**
     *
     * @param source
     * @param destination
     * @return
     */


    @Override
    public List<Train> getTrainBySourceAndDestination(String source, String destination) {
        return getAllTrain().stream()
                .filter(a -> (a.getSource().equals(source) && a.getDestination().equals(destination)))
                .collect(Collectors.toList());
    }
}
