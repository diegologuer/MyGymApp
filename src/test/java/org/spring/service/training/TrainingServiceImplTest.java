package org.spring.service.training;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Training;
import org.spring.repository.trainee.TraineeDAO;
import org.spring.repository.trainer.TrainerDAO;
import org.spring.repository.training.TrainingDAO;
import org.spring.repository.trainingType.TrainingTypeDAO;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainingServiceImplTest {

    @Mock
    private TrainingTypeDAO trainingTypeDAO;
    @Mock
    private TrainingDAO trainingDAO;
    @Mock
    private TraineeDAO traineeDAO;
    @Mock
    private TrainerDAO trainerDAO;

    @InjectMocks
    TrainingServiceImpl sut;

    @Test
    void givenValidArgs_WhenCreatingTraining_ThenTrainingIsCreated() {
        //Arrange
        //Existence of traineeId, trainingTypeId and trainerId is validated from dependencies
        String trainingName = "Cardio Training";
        Date trainingDate = new Date(124, 12, 23);
        int traineeId = 21;
        int trainingTypeId = 1;
        int trainingDuration = 20;
        int trainerId = 4;
        int expectedTrainingId = 45;
        Training expectedTraining = new Training(expectedTrainingId,trainingName, trainingDate, traineeId,trainingTypeId,trainingDuration,trainerId);

        when(trainingDAO.nextAvailableId()).thenReturn(expectedTrainingId);
        when(trainingDAO.save(expectedTraining)).thenReturn(expectedTrainingId);

        //Act
        int actualTrainingId = sut.createTraining(trainingName, trainingDate, traineeId, trainingTypeId,
                trainingDuration,trainerId);

        //Assert
        assertEquals(expectedTrainingId, actualTrainingId);
    }

    @Test
    void getTrainingById() {
        //Arrange
        //Existence of training is validated from dependency
        int trainingId = 6;
        String trainingName = "Cardio Training";
        Date trainingDate = new Date(124, 12, 23);
        int traineeId = 21;
        int trainingTypeId = 1;
        int trainingDuration = 20;
        int trainerId = 4;

        Training expectedTraining = new Training(trainingId,trainingName, trainingDate,
                traineeId,trainingTypeId,trainingDuration,trainerId);
        when(trainingDAO.getById(trainingId)).thenReturn(expectedTraining);

        //Act
        Training actualTraining = sut.getTrainingById(trainingId);

        //Assert
        assertEquals(expectedTraining, actualTraining);
    }
}