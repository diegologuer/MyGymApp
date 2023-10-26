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
        storage = mock(Storage.class);
        trainingTypeDAO = new TrainingTypeDAOImpl(storage);
    }

    @Test
    public void givenTrainingTypeMapWithTrainingType_whenGetTrainingTypeById_thenShouldReturnTrainingType() {
        TrainingType trainingType = new TrainingType(1, "Cardio Workout");
        when(storage.getTrainingTypeMap()).thenReturn(mock(Map.class));
        when(storage.getTrainingTypeMap().get(1)).thenReturn(trainingType);
        TrainingType result = trainingTypeDAO.getById(1);
        assertNotNull(result);
        assertEquals(trainingType, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTrainingTypeMap_whenGetTrainingTypeById_thenShouldThrowNoSuchElementException() {
        when(storage.getTrainingTypeMap()).thenReturn(mock(Map.class));
        trainingTypeDAO.getById(1);
    }

    @Test
    public void givenNextAvailableTrainingTypeId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        when(storage.nextAvailableTrainingTypeId()).thenReturn(1);
        int result = trainingTypeDAO.nextAvailableId();
        assertEquals(1, result);
        verify(storage).nextAvailableTrainingTypeId();
    }
}
