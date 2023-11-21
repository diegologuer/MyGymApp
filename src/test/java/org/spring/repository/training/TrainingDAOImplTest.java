package org.spring.repository.training;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Training;
import org.spring.storage.Storage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainingDAOImplTest {

    @Mock
    private Storage storage;

    @InjectMocks
    private TrainingDAOImpl sut;


    @Test
    void GivenValidArgs_WhenSavingTraining_ThenTrainingIsSaved() {
        // Arrange
        int expectedTrainingId = 14;
        Training training = new Training(expectedTrainingId, "Cardio",
                new Date(123, 11, 20, 12, 0),
                1,4,45,5);
        Map<Integer, Training> trainingMap = new HashMap<>();
        trainingMap.put(expectedTrainingId, training);
        when(storage.getTrainingMap()).thenReturn(trainingMap);

        // Act
        int actualTrainingId = sut.save(training);

        // Assert
        assertEquals(expectedTrainingId, actualTrainingId);
    }

    @Test
    void GivenNullArg_WhenSavingTraining_ThenThrowsAnException() {
        // Act and Assert
        assertThrows(RuntimeException.class, () -> sut.save(null), "Error saving Training");
    }

    @Test
    void GivenValidId_WhenGettingTrainingById_ThenTrainingIsReturned() {
        // Arrange
        int trainingId = 4;
        Training expectedTraining = new Training(trainingId, "Cardio",
                new Date(123, 11, 20, 12, 0),
                1,4,45,5);
        Map<Integer, Training> trainingMap = new HashMap<>();
        trainingMap.put(trainingId, expectedTraining);
        when(storage.getTrainingMap()).thenReturn(trainingMap);

        // Act
        Training actualTraining = sut.getById(trainingId);

        // Assert
        assertEquals(expectedTraining, actualTraining);
    }

    @Test
    void GivenNotValidId_WhenGettingTrainingById_ThenThrowsException() {
        // Arrange
        int trainingId = 4;
        when(storage.getTrainingMap()).thenReturn(new HashMap<>());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> sut.getById(trainingId));
    }


    @Test
    void whenGettingNextAvailableId_ThenNextAvailableIdIsReturned() {
        // Arrange
        int expectedAvailableId = 12;
        when(storage.nextAvailableTrainingId()).thenReturn(expectedAvailableId);

        // Act
        int actualAvailableId = sut.nextAvailableId();

        // Assert
        assertEquals(expectedAvailableId, actualAvailableId);
    }
}