package org.spring.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.Training;
import org.spring.service.trainee.TraineeService;
import org.spring.service.trainer.TrainerService;
import org.spring.service.training.TrainingService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacadeImplTest {

    @Mock
    private TraineeService traineeService;
    @Mock
    private TrainerService trainerService;
    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private FacadeImpl sut;

    @Test
    void givenValidArgs_WhenCreatingTrainee_ThenTraineeIsCreated() {
        //Arrange
        int expectedTraineeId = 3;
        String name = "John";
        String lastname = "Doe";
        Date dateOfBirth = new Date(99, 11, 7);
        String address = "123 Main St";
        when(traineeService.createTrainee(name, lastname, dateOfBirth, address))
                .thenReturn(expectedTraineeId);

        //Act
        int actualTraineeId = sut.createTrainee(name, lastname, dateOfBirth, address);

        //Assert
        assertEquals(expectedTraineeId, actualTraineeId);
        verify(traineeService).createTrainee(name, lastname, dateOfBirth, address);
    }

    @Test
    void givenValidArgs_WhenUpdatingTrainee_ThenTraineeIsUpdated() {
        // Arrange
        int traineeId = 1;
        Date dateOfBirth = new Date(95, 1, 7);
        String address = "456 Oak St";
        int userId = 3;
        Trainee expectedTrainee = new Trainee(traineeId, dateOfBirth, address, userId);

        when(traineeService.updateTrainee(traineeId, dateOfBirth, address))
                .thenReturn(expectedTrainee);

        // Act
        Trainee actualTrainee = sut.updateTrainee(traineeId, dateOfBirth, address);

        // Assert
        assertEquals(expectedTrainee, actualTrainee);
        verify(traineeService).updateTrainee(traineeId, dateOfBirth, address);
    }

    @Test
    void givenValidArgs_WhenDeletingTrainee_ThenTraineeIsDeleted() {
        // Arrange
        int traineeId = 1;
        Trainee expectedTrainee = new Trainee(1, new Date(74, 4, 24), "12 Main St", 3);

        when(traineeService.deleteTrainee(traineeId))
                .thenReturn(expectedTrainee);

        // Act
        Trainee actualTrainee = sut.deleteTrainee(traineeId);

        // Assert
        assertEquals(expectedTrainee, actualTrainee);
        verify(traineeService).deleteTrainee(traineeId);
    }

    @Test
    void givenValidId_WhenGettingTraineeById_ThenTraineeIsReturned() {
        // Arrange
        int traineeId = 1;
        Trainee expectedTrainee = new Trainee(1, new Date(74, 4, 24), "12 Main St", 3);

        when(traineeService.getTraineeById(traineeId))
                .thenReturn(expectedTrainee);

        // Act
        Trainee actualTrainee = sut.getTraineeById(traineeId);

        // Assert
        assertEquals(expectedTrainee, actualTrainee);
        verify(traineeService).getTraineeById(traineeId);
    }

    @Test
    void givenValidArgs_WhenCreatingTrainer_ThenTrainerIsReturned() {
        // Arrange
        int expectedTrainerId = 2;
        String name = "Trainer";
        String lastname = "One";
        int specialization = 3;

        when(trainerService.createTrainer(name, lastname, specialization))
                .thenReturn(expectedTrainerId);

        // Act
        int actualTrainerId = sut.createTrainer(name, lastname, specialization);

        // Assert
        assertEquals(expectedTrainerId, actualTrainerId);
        verify(trainerService).createTrainer(name, lastname, specialization);
    }


    @Test
    void givenValidArgs_WhenUpdatingTrainer_ThenTrainerIsUpdated() {
        // Arrange
        int trainerId = 1;
        int specialization = 4;
        Trainer expectedTrainer = new Trainer(trainerId, 4, specialization);

        when(trainerService.updateTrainer(trainerId, specialization))
                .thenReturn(expectedTrainer);

        // Act
        Trainer actualTrainer = sut.updateTrainer(trainerId, specialization);

        // Assert
        assertEquals(expectedTrainer, actualTrainer);
        verify(trainerService).updateTrainer(trainerId, specialization);
    }

    @Test
    void givenValidId_WhenGettingTrainerById_ThenTrainerIsReturned() {
        // Arrange
        int trainerId = 1;
        Trainer expectedTrainer = new Trainer(trainerId, 4, 5);

        when(trainerService.getTrainerById(trainerId))
                .thenReturn(expectedTrainer);

        // Act
        Trainer actualTrainer = sut.getTrainerById(trainerId);

        // Assert
        assertEquals(expectedTrainer, actualTrainer);
        verify(trainerService).getTrainerById(trainerId);
    }

    @Test
    void givenValidArgs_WhenCreatingTraining_ThenTrainingIsCreated() {
        // Arrange
        int expectedTrainingId = 5;
        String trainingName = "Spinning";
        Date trainingDate = new Date(123, 3, 15);
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 45;
        int trainerId = 1;

        when(trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId))
                .thenReturn(expectedTrainingId);

        // Act
        int actualTrainingId = sut.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);

        // Assert
        assertEquals(expectedTrainingId, actualTrainingId);
        verify(trainingService).createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
    }

    @Test
    void givenValidId_WhenGettingTrainingById_ThenTrainingIsReturned() {
        // Arrange
        int trainingId = 5;
        Training expectedTraining = new Training(trainingId, "KickBoxing Training", new Date(123, 3, 15), 1, 2, 35, 1);

        when(trainingService.getTrainingById(trainingId))
                .thenReturn(expectedTraining);

        // Act
        Training actualTraining = sut.getTrainingById(trainingId);

        // Assert
        assertEquals(expectedTraining, actualTraining);
        verify(trainingService).getTrainingById(trainingId);
    }
}