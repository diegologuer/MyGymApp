package spring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.spring.model.Training;
import org.spring.repository.training.TrainingDAO;
import org.spring.repository.training.TrainingDAOImpl;
import org.spring.storage.Storage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TrainingDAOImplTest {
    private Storage storage;
    private TrainingDAO trainingDAO;

    @Before
    public void setUp() {
        storage = mock(Storage.class);
        trainingDAO = new TrainingDAOImpl(storage);
    }

    @Test
    public void saveTest(){
        Training training = new Training(8, "Running", new Date(), 1, 1, 20, 1);
        when(storage.getTrainingMap()).thenReturn(new HashMap<>());
        int saveTrainingId  = trainingDAO.save(training);
        assertEquals(8, saveTrainingId);
    }
    @Test
    public void testGetTrainingById() {
        Training training = new Training(1, "Cardio Workout", new Date(), 1, 1, 60, 1);
        when(storage.getTrainingMap()).thenReturn(mock(Map.class));
        when(storage.getTrainingMap().get(1)).thenReturn(training);
        Training result = trainingDAO.getById(1);
        assertNotNull(result);
        assertEquals(training, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetTrainingByIdNotFound() {
        when(storage.getTrainingMap()).thenReturn(mock(Map.class));
        trainingDAO.getById(1);
    }

    @Test
    public void testNextAvailableId() {
        when(storage.nextAvailableTrainingId()).thenReturn(1);
        int result = trainingDAO.nextAvailableId();
        assertEquals(1, result);
        verify(storage).nextAvailableTrainingId();
    }
}
