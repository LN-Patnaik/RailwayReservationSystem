package com.TrainComponent.Controller;

import com.TrainComponent.model.Train;
import com.TrainComponent.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    TrainService trainService;

    @GetMapping("/hello")
    @Operation(summary = "Demo hello world")
    public String displayHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/trains/{trainNo}")
    @Operation(summary = "This is to search train by train no.")
    public Train getTrainByTrainNo(@PathVariable String trainNo) {
        Train getTrain = trainService.getTrainByNo(trainNo);
        return getTrain;
    }

    @GetMapping("/trains")
    @Operation(summary = "This is to show all trains")
    public List<Train> getAllTrains() {
        List<Train> trains = trainService.getAllTrain();
        return trains;
    }

    @PostMapping("/addTrain")
    @Operation(summary = "This is to add train")
    public Train addTrain(@RequestBody Train train) {
        Train addedTrain = trainService.addTrain(train);
        return addedTrain;
    }

    @PutMapping("/updateTrain")
    @Operation(summary = "This is to update train")
    public Train updateTrain(@RequestBody Train train) {
        Train updatedTrain = trainService.updateTrain(train);
        return updatedTrain;
    }

    @DeleteMapping("/delete/{trainNo}")
    @Operation(summary = "This is to delete train by train no.")
    public String deleteTrainByTrainNo(@PathVariable String trainNo) {
        trainService.deleteByTrainNo(trainNo);
        return "Train details deleted by" + trainNo;
    }

    @GetMapping("/train/{trainName}")
    @Operation(summary = "This is to search train by train name")
    public Train getTrainByTrainName(@PathVariable String trainName) {
        Train train = trainService.getTrainByTrainName(trainName);
        return train;
    }

    @GetMapping("/getTrainsBySourceAndDestination")
    @Operation(summary = "This is to search trains by source and destination")
    public List<Train> getTrainsBySourceAndDestination(@QueryParam("source") String source, @QueryParam("destination") String destination)
    {
        List<Train> allTrains= trainService.getTrainBySourceAndDestination(source, destination);
        return allTrains;
    }

}

