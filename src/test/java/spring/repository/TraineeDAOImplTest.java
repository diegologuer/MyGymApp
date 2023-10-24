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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TraineeDAOImplTest {
    private Storage storage;
    private TraineeDAO traineeDAO;

    @Before
    public void setUp() {
        storage = mock(Storage.class);
        traineeDAO = new TraineeDAOImpl(storage);
    }

    @Test
    public void testSaveTrainee() {
        Trainee trainee = new Trainee(1, new Date(), "123 Gym St, Fitness City", 1);
        Map<Integer, Trainee> traineeMap = new HashMap<>();
        when(storage.getTraineeMap()).thenReturn(traineeMap);
        doNothing().when(storage.getTraineeMap()).put(1, trainee);

        int result = traineeDAO.save(trainee);

        assertEquals(1, result);
        verify(storage.getTraineeMap()).put(1, trainee);
    }


    @Test(expected = RuntimeException.class)
    public void testSaveTraineeDuplicateId() {
        Trainee trainee = new Trainee(1, new Date(), "123 Gym St, Fitness City", 1);
        Map<Integer, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(1, trainee);
        when(storage.getTraineeMap()).thenReturn(traineeMap);

        traineeDAO.save(trainee);
    }

    @Test
    public void testGetTraineeById() {
        Trainee trainee = new Trainee(1, new Date(), "123 Gym St, Fitness City", 1);
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());
        when(storage.getTraineeMap().get(1)).thenReturn(trainee);

        Trainee result = traineeDAO.getById(1);

        assertNotNull(result);
        assertEquals(trainee, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetTraineeByIdNotFound() {
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        traineeDAO.getById(1);
    }

    @Test
    public void testRemoveTraineeById() {
        Trainee trainee = new Trainee(1, new Date(), "123 Gym St, Fitness City", 1);
        Map<Integer, Trainee> traineeMap = new HashMap<>();
        traineeMap.put(1, trainee);
        when(storage.getTraineeMap()).thenReturn(traineeMap);

        Trainee result = traineeDAO.removeById(1);

        assertNotNull(result);
        assertEquals(trainee, result);
        verify(storage.getTraineeMap()).remove(1);
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
