package org.spring.repository.trainee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Trainee;
import org.spring.storage.Storage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraineeDAOImplTest {

    @Mock
    private Storage storage;

    @InjectMocks
    private TraineeDAOImpl sut;

    @Test
    void GivenValidArgs_WhenSavingTrainee_ThenTraineeIsSaved() {
        //Arrange
        int expectedTraineeId = 14;
        Trainee trainee = new Trainee(expectedTraineeId, new Date(111, 12,22), "123 Main St", 4);
        Map<Integer, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(expectedTraineeId, trainee);
        when(storage.getTraineeMap()).thenReturn(traineeMap);

        //Act
        int actualTraineeId = sut.save(trainee);

        //Assert
        assertEquals(expectedTraineeId, actualTraineeId);
    }

    @Test
    void GivenNullArg_WhenSavingTrainee_ThenThrowsAnException() {
        // Act and Assert
        assertThrows(RuntimeException.class, () -> sut.save(null), "Error saving trainee");
    }

    @Test
    void GivenValidId_WhenGettingTraineeById_ThenTraineeIsReturn() {
        //Arrange
        int traineeId = 4;
        Trainee expectedTrainee = new Trainee(traineeId, new Date(111, 12,22), "123 Main St", 4);
        Map<Integer, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(traineeId, expectedTrainee);
        when(storage.getTraineeMap()).thenReturn(traineeMap);

        //Act
        Trainee actualTrainee = sut.getById(traineeId);

        //Assert
        assertEquals(expectedTrainee, actualTrainee);
    }

    @Test
    void GivenNotValidId_WhenGettingTraineeById_ThenThrowsException() {
        //Arrange
        int traineeId = 4;
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        //Act and Assert
        assertThrows(NoSuchElementException.class, () -> sut.getById(traineeId));
    }

    @Test
    void GivenValidId_WhenRemovingTraineeById_ThenTraineeIsRemoved() {
        //Arrange
        int traineeId = 4;
        Trainee expectedTrainee = new Trainee(traineeId, new Date(111, 12,22), "123 Main St", 4);
        Map<Integer, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(traineeId, expectedTrainee);
        when(storage.getTraineeMap()).thenReturn(traineeMap);

        //Act
        Trainee actualTrainee = sut.removeById(traineeId);

        //Assert
        assertEquals(expectedTrainee, actualTrainee);
    }

    @Test
    void GivenNotValidId_WhenRemovingTraineeById_ThenThrowsException() {
        //Arrange
        int traineeId = 4;
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        //Act and Assert
        assertThrows(NoSuchElementException.class, () -> sut.removeById(traineeId));
    }

    @Test
    void nextAvailableId() {
        //Arrange
        int expectedAvailaleId = 12;
        when(storage.nextAvailableTraineeId()).thenReturn(expectedAvailaleId);

        //Act
        int actualAvailableId = sut.nextAvailableId();

        //Arrange
        assertEquals(expectedAvailaleId, actualAvailableId);
    }
}