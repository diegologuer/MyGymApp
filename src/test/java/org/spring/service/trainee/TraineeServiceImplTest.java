package org.spring.service.trainee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.repository.trainee.TraineeDAO;
import org.spring.repository.user.UserDAO;
import org.spring.service.profile.CredentialsService;

import javax.swing.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraineeServiceImplTest {

    @Mock
    private TraineeDAO traineeDAO;
    @Mock
    private CredentialsService credentialsService;
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    TraineeServiceImpl sut;

    @Test
    void givenValidArgs_whenCreatingTrainee_TraineeIsCreated() {
        //Arrange
        String name = "John";
        String lastname = "Doe";
        Date dateOfBirth = new Date(99, 4, 15);
        String address = "123 Muscle St";
        int userId = 45;
        int expectedId = 40;
        Trainee expectedTrainee = new Trainee(expectedId, dateOfBirth, address, userId);

        when(credentialsService.createPersonProfile(name, lastname)).thenReturn(userId);
        when(traineeDAO.nextAvailableId()).thenReturn(expectedId);
        when(traineeDAO.save(expectedTrainee)).thenReturn(expectedId);

        //Act
        int actualId = sut.createTrainee(name, lastname, dateOfBirth, address);

        //Assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void givenValidArgs_WhenUpdatingTrainee_ThenTraineeIsUpdated() {
        int traineeId = 3;
        Date dateOfBirth = new Date(99, 1, 1); // Use an appropriate date
        String address = "456 Oak St";
        Trainee oldTrainee = new Trainee(traineeId, dateOfBirth, "123 Main St", 1);
        Trainee expectedTrainee = new Trainee(traineeId, dateOfBirth, address, 1);

        when(traineeDAO.getById(traineeId)).thenReturn(oldTrainee);

        // Act
        Trainee actualTrainee = sut.updateTrainee(traineeId, dateOfBirth, address);

        // Assert
        assertEquals(expectedTrainee, actualTrainee);
    }

    @Test
    void givenValidId_WhenDeletingTrainee_ThenTraineeIsDeleted() {
        int traineeId = 8;
        int userId = 6;
        Trainee expectedTrainee = new Trainee(traineeId, new Date(87, 12,5), "45 Main St", userId);
        when(traineeDAO.getById(traineeId)).thenReturn(expectedTrainee);

        //Act
        Trainee actualTrainee = sut.deleteTrainee(traineeId);

        //Assert
        assertEquals(expectedTrainee, actualTrainee);
    }

    @Test
    void getTraineeById() {
        //Arrange
        int traineeId = 4;
        Trainee expectedTrainee = new Trainee(traineeId, new Date(87, 12,5), "45 Main St", 5);
        when(traineeDAO.getById(traineeId)).thenReturn(expectedTrainee);

        //Act
        Trainee actualTrainee = sut.getTraineeById(traineeId);

        //Assert
        assertEquals(expectedTrainee, actualTrainee);
    }

}