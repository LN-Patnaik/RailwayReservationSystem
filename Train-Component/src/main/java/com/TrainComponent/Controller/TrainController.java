package com.TrainComponent.Controller;

import com.TrainComponent.model.Train;
import com.TrainComponent.service.TrainService;
import com.TrainComponent.service.TrainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/train")
    public class TrainController {

        @Autowired
        TrainService trainService;

        @RequestMapping("/hello")
        public String displayHelloWorld()
        {
            return "Hello World";
        }

        @RequestMapping("/trains/{trainNo}")
        public Train getTrainByTrainNo(@PathVariable String trainNo)
        {
            Train getTrain= trainService.getTrainByNo(trainNo);
            return getTrain;
        }

        @RequestMapping("/trains")
        public List<Train> getAllTrains()
        {
            List<Train> trains=trainService.getAllTrain();
            return  trains;
        }

        @PostMapping("/addTrain")
        public Train addTrain(@RequestBody   Train train)
        {
            Train addedTrain=trainService.addTrain(train);
            return  addedTrain;
        }

        @PutMapping("/updateTrain")
        public Train updateTrain( @RequestBody Train train)
        {
            Train updatedTrain= trainService.updateTrain(train);
            return  updatedTrain;
        }

        @DeleteMapping("/delete/{trainNo}")
        public String deleteTrainByTrainNo(@PathVariable String trainNo)
        {
            trainService.deleteByTrainNo(trainNo);
            return "Train details deleted by"+trainNo;
        }
    }

