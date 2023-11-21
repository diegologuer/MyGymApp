package org.spring.repository.trainer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Trainer;
import org.spring.storage.Storage;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainerDAOImplTest {

    @Mock
    Storage storage;

    @InjectMocks
    TrainerDAOImpl sut;

    @Test
    void GivenValidArgs_WhenSavingTrainer_ThenTrainerIsSaved() {
        // Arrange
        int expectedTrainerId = 14;
        Trainer trainer = new Trainer(expectedTrainerId,4,5);
        Map<Integer, Trainer> trainerMap = new HashMap<>();
        trainerMap.put(expectedTrainerId, trainer);
        when(storage.getTrainerMap()).thenReturn(trainerMap);

        // Act
        int actualTrainerId = sut.save(trainer);

        // Assert
        assertEquals(expectedTrainerId, actualTrainerId);
        verify(storage, times(2)).getTrainerMap();
    }

    @Test
    void GivenNullArg_WhenSavingTrainer_ThenThrowsAnException() {
        // Act and Assert
        assertThrows(RuntimeException.class, () -> sut.save(null), "Error saving trainer");
    }

    @Test
    void GivenValidId_WhenGettingTrainerById_ThenTrainerIsReturned() {
        // Arrange
        int trainerId = 4;
        Trainer expectedTrainer = new Trainer(trainerId, 12, 1);
        Map<Integer, Trainer> trainerMap = new HashMap<>();
        trainerMap.put(trainerId, expectedTrainer);
        when(storage.getTrainerMap()).thenReturn(trainerMap);

        // Act
        Trainer actualTrainer = sut.getById(trainerId);

        // Assert
        assertEquals(expectedTrainer, actualTrainer);
        verify(storage).getTrainerMap();
    }

    @Test
    void GivenNotValidId_WhenGettingTrainerById_ThenThrowsException() {
        // Arrange
        int trainerId = 4;
        when(storage.getTrainerMap()).thenReturn(new HashMap<>());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> sut.getById(trainerId));
    }

    @Test
    void whenRequestingNextAvailableId_ThenNextAvailableIdIsReturned() {
        // Arrange
        int expectedAvailableId = 12;
        when(storage.nextAvailableTrainerId()).thenReturn(expectedAvailableId);

        // Act
        int actualAvailableId = sut.nextAvailableId();

        // Assert
        assertEquals(expectedAvailableId, actualAvailableId);
        verify(storage).nextAvailableTrainerId();
    }
}