package org.spring.service.trainer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Trainer;
import org.spring.model.TrainingType;
import org.spring.repository.trainer.TrainerDAO;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.spring.service.profile.CredentialsService;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerServiceImplTest {

    @Mock
    private TrainerDAO trainerDAO;
    @Mock
    private CredentialsService credentialsService;
    @Mock
    private TrainingTypeDAO trainingTypeDAO;

    @InjectMocks
    TrainerServiceImpl sut;

    @Test
    void givenValidArgs_WhenCreatingTrainer_ThenTrainerIsCreated() {
        //Arrange
        String name = "John";
        String lastname = "Doe";
        int specialization = 5;
        int expectedTrainerId = 2;
        int userId = 4;
        when(credentialsService.createPersonProfile(name, lastname)).thenReturn(userId);
        when(trainerDAO.nextAvailableId()).thenReturn(expectedTrainerId);
        when(trainerDAO.save(any(Trainer.class))).thenReturn(expectedTrainerId);

        //Act
        int actualTrainerId = sut.createTrainer(name, lastname, specialization);

        //Assert
        assertEquals(expectedTrainerId, actualTrainerId);
    }

    @Test
    void givenNegativeSpecialization_WhenCreatingTrainer_ThenThrowsException() {
        //Arrange
        int specialization = -5;

        //Act and Assert
        assertThrows(NoSuchElementException.class, ()-> sut.createTrainer("John", "Doe", specialization));
    }

    @Test
    void givenValidArgs_WhenUpdatingTrainer_ThenTrainerIsUpdated() {
        //Arrange
        int trainerId = 3;
        int oldSpecialization = 5;
        int newSpecialization = 2;
        Trainer trainer = new Trainer(trainerId, 4, oldSpecialization);
        Trainer expectedTrainer = new Trainer(trainerId, 4, newSpecialization);
        when(trainerDAO.getById(trainerId)).thenReturn(trainer);

        //Act
        Trainer actualTrainer = sut.updateTrainer(trainerId, newSpecialization);

        //Assert
        assertEquals(expectedTrainer, actualTrainer);
    }

    @Test
    void getTrainerById() {
        //Arrange
        int trainerId = 4;
        Trainer expectedTrainer = new Trainer(trainerId, 4, 5);
        when(trainerDAO.getById(trainerId)).thenReturn(expectedTrainer);

        //Act
        Trainer actualTrainer = sut.getTrainerById(trainerId);

        //Assert
        assertEquals(expectedTrainer, actualTrainer);
    }


}