package com.TrainComponent;

import com.TrainComponent.model.Train;
import com.TrainComponent.repository.TrainRepository;
import com.TrainComponent.service.TrainServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class TrainServiceImplTest {

    @InjectMocks
    TrainServiceImpl trainService;

    @Mock
    TrainRepository trainRepository;

@Before
public void setup(){

}

    @Test
    public void testGetTrainByTrainName()
{
    String trainName = "TRAIN123";
    Train train = new Train();
    train.setTrainName(trainName);
    Mockito.when(trainRepository.getTrainByTrainName(trainName)).thenReturn(train);

    Train fetchedTrain = trainService.getTrainByTrainName(trainName);

    Assert.assertEquals(trainName, fetchedTrain.getTrainName());
}

    @Test
    public void testGetTrainByTrainNo()
    {
        String trainNo = "TRAIN123";
        Train train = new Train();
        train.setTrainNo(trainNo);
        Optional trainOpt = Optional.of(train);
        Mockito.when(trainRepository.findById(trainNo)).thenReturn(trainOpt);

        Train fetchedTrain = trainService.getTrainByNo(trainNo);

        Assert.assertEquals(trainNo, fetchedTrain.getTrainNo());
    }

    @Test
    public void testGetAllTrains(){

    List<Train> trainList = new ArrayList<>();
        Train train = new Train();
        train.setTrainNo("TR123");
        trainList.add(train);

        Train train1 = new Train();
        train.setTrainNo("TR");
        trainList.add(train1);

        Train train2 = new Train();
        train.setTrainNo("TR13");
        trainList.add(train2);

        Train train3 = new Train();
        train.setTrainNo("TR12");
        trainList.add(train3);

        Mockito.when(trainRepository.findAll()).thenReturn(trainList);

        List<Train> fetchedList = trainService.getAllTrain();
        Assert.assertEquals(4, fetchedList.size());
    }

    @Test
    public void testAddTrain()
    {
        String trainNo = "123";
        Train train = new Train();
        train.setTrainNo(trainNo);
        train.setDestination("BBS");

        Mockito.when(trainRepository.save(train)).thenReturn(train);

        Train addedTrain = trainService.addTrain(train);

        Assert.assertNotNull(addedTrain);
        Assert.assertEquals(trainNo, addedTrain.getTrainNo());
        Assert.assertEquals("BBS", addedTrain.getDestination());
    }

    @Test
    public void testDeleteTrain()
    {
        String trainNo = "TRAIN123";
        Train train = new Train();
        train.setTrainNo(trainNo);
        train.setDestination("BBS");

        Mockito.doNothing().when(trainRepository).deleteById(trainNo);

        String message = trainService.deleteByTrainNo(trainNo);

        Assert.assertEquals("Deleted Successfully", message);
    }

    @Test
    public void testUpdateTrain()
    {
        Train train = new Train();
        train.setDestination("Chennai");

        Mockito.when(trainRepository.save(train)).thenReturn(train);

        Train updatedTrain = trainService.updateTrain(train);

        Assert.assertNotNull(updatedTrain);
        Assert.assertEquals("Chennai", updatedTrain.getDestination());
    }

    @Test
    public void testGetTrainsBySourceAndDestination(){

        List<Train> trainList2 = new ArrayList<>();
        Train train = new Train();
        train.setSource("Chennai");
        train.setDestination("New Delhi");
        trainList2.add(train);


        Mockito.when(trainRepository.findAll().stream().filter(a->a.getSource().equals(train.getSource()) &&
                        a.getDestination().equals(train.getDestination())).collect(Collectors.toList()))
                .thenReturn(trainList2);

        List<Train> fetchedList = trainService.getTrainBySourceAndDestination("Chennai", "New Delhi");
        Assert.assertEquals(1, fetchedList.size());
    }
}
