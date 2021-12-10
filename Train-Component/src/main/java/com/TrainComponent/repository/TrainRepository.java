package com.TrainComponent.repository;

import com.TrainComponent.model.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends MongoRepository<Train,String> {

    @Query("{'trainName': ?0}")
    public Train getTrainByTrainName(String trainName);


}
