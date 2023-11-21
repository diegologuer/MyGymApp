package org.spring.repository.trainingType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.TrainingType;
import org.spring.storage.Storage;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainingTypeDAOImplTest {

    @Mock
    Storage storage;

    @InjectMocks
    TrainingTypeDAOImpl sut;

    @Test
    void givenValidId_WhenGettingTrainingTypeById_ThenTrainingTypeIsReturned() {
        //Arrange
        int trainingTypeId = 1;
        TrainingType expectedTrainingType = new TrainingType(trainingTypeId,
                "KickBoxing Training");
        Map<Integer, TrainingType> trainingTypeMap = new HashMap<>();
        trainingTypeMap.put(trainingTypeId, expectedTrainingType);
        when(storage.getTrainingTypeMap()).thenReturn(trainingTypeMap);

        //Act
        TrainingType actualTrainingType = sut.getById(trainingTypeId);

        //Assert
        assertEquals(expectedTrainingType, actualTrainingType);
        verify(storage).getTrainingTypeMap();
    }

    @Test
    void GivenNotValidId_WhenGettingTrainingTypeById_ThenThrowsException() {
        //Arrange
        int trainingTypeId = 4;
        when(storage.getTrainingTypeMap()).thenReturn(new HashMap<>());

        //Act and Assert
        assertThrows(NoSuchElementException.class, () -> sut.getById(trainingTypeId));
    }

    @Test
    void whenRequestNextAvailableId_ThenNextAvailableIdIsReturned() {
        //Arrange
        int expectedAvailableId = 7;
        when(storage.nextAvailableTrainingTypeId()).thenReturn(expectedAvailableId);

        //Act
        int actualAvailableId = sut.nextAvailableId();

        //Arrange
        assertEquals(expectedAvailableId, actualAvailableId);
        verify(storage).nextAvailableTrainingTypeId();
    }
}