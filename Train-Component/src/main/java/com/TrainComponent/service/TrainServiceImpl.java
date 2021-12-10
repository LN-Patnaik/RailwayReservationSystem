package com.TrainComponent.service;

import com.TrainComponent.model.Train;
import com.TrainComponent.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements  TrainService{

    @Autowired
    TrainRepository trainRepository;

    @Override
    public Train getTrainByNo(String trainNo) {
        Train train =trainRepository.findById(trainNo).get();
        return train;
    }



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

    @Override
    public Train getTrainByTrainName(String trainName) {
        Train train = trainRepository.getTrainByTrainName(trainName);
        return train;
    }

    @Override
    public List<Train> getTrainBySourceAndDestination(String source, String destination) {
        return getAllTrain().stream()
                .filter(a -> (a.getSource().equals(source) && a.getDestination().equals(destination)))
                .collect(Collectors.toList());
    }
}
