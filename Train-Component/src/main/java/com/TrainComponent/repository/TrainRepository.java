package com.TrainComponent.repository;

import com.TrainComponent.model.Train;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainRepository extends MongoRepository<Train,String> {
}
