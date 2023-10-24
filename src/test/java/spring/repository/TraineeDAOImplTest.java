package spring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.spring.model.Trainee;
import org.spring.repository.trainee.TraineeDAO;
import org.spring.repository.trainee.TraineeDAOImpl;
import org.spring.storage.Storage;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class TraineeDAOImplTest {
    private Storage storage;
    private TraineeDAO traineeDAO;

    @Before
    public void setUp() {
        storage = mock(Storage.class);
        traineeDAO = new TraineeDAOImpl(storage);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetTraineeByIdNotFound() {
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        traineeDAO.getById(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveTraineeByIdNotFound() {
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        traineeDAO.removeById(1);
    }

    @Test
    public void testNextAvailableId() {
        when(storage.nextAvailableTraineeId()).thenReturn(1);

        int result = traineeDAO.nextAvailableId();

        assertEquals(1, result);
        verify(storage).nextAvailableTraineeId();
    }
}
