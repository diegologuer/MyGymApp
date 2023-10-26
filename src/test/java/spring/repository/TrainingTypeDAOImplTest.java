package spring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.TrainingType;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.spring.repository.trainingType.TrainingTypeDAOImpl;
import org.spring.storage.Storage;

import java.util.Map;
import java.util.NoSuchElementException;

public class TrainingTypeDAOImplTest {
    private Storage storage;
    private TrainingTypeDAO systemUnderTest;

    @Before
    public void setUp() {
        // Arrange
        storage = mock(Storage.class);
        systemUnderTest = new TrainingTypeDAOImpl(storage);
    }

    @Test
    public void givenTrainingTypeMapWithTrainingType_whenGetTrainingTypeById_thenShouldReturnTrainingType() {
        // Arrange
        TrainingType expectedtrainingType = new TrainingType(1, "Cardio Workout");
        when(storage.getTrainingTypeMap()).thenReturn(mock(Map.class));
        when(storage.getTrainingTypeMap().get(1)).thenReturn(expectedtrainingType);

        // Act
        TrainingType actualTrainingType = systemUnderTest.getById(1);

        // Assert
        assertNotNull(actualTrainingType);
        assertEquals(expectedtrainingType, actualTrainingType);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTrainingTypeMap_whenGetTrainingTypeById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTrainingTypeMap()).thenReturn(mock(Map.class));

        // Act and Assert
        systemUnderTest.getById(1);
    }

    @Test
    public void givenNextAvailableTrainingTypeId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        int expectedAvailableId = 1;
        when(storage.nextAvailableTrainingTypeId()).thenReturn(1);

        // Act
        int result = systemUnderTest.nextAvailableId();

        // Assert
        assertEquals(expectedAvailableId, result);
        verify(storage).nextAvailableTrainingTypeId();
    }
}
