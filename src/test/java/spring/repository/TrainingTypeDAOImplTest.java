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
    private TrainingTypeDAO trainingTypeDAO;

    @Before
    public void setUp() {
        // Arrange
        storage = mock(Storage.class);
        trainingTypeDAO = new TrainingTypeDAOImpl(storage);
    }

    @Test
    public void givenTrainingTypeMapWithTrainingType_whenGetTrainingTypeById_thenShouldReturnTrainingType() {
        // Arrange
        TrainingType trainingType = new TrainingType(1, "Cardio Workout");
        when(storage.getTrainingTypeMap()).thenReturn(mock(Map.class));
        when(storage.getTrainingTypeMap().get(1)).thenReturn(trainingType);

        // Act
        TrainingType result = trainingTypeDAO.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals(trainingType, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTrainingTypeMap_whenGetTrainingTypeById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTrainingTypeMap()).thenReturn(mock(Map.class));

        // Act and Assert
        trainingTypeDAO.getById(1);
    }

    @Test
    public void givenNextAvailableTrainingTypeId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        when(storage.nextAvailableTrainingTypeId()).thenReturn(1);

        // Act
        int result = trainingTypeDAO.nextAvailableId();

        // Assert
        assertEquals(1, result);
        verify(storage).nextAvailableTrainingTypeId();
    }
}
