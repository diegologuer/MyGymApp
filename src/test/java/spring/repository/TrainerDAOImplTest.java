package spring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.Trainer;
import org.spring.repository.trainer.TrainerDAO;
import org.spring.repository.trainer.TrainerDAOImpl;
import org.spring.storage.Storage;

import java.util.Map;
import java.util.NoSuchElementException;

public class TrainerDAOImplTest {
    private Storage storage;
    private TrainerDAO trainerDAO;

    @Before
    public void setUp() {
        storage = mock(Storage.class);
        trainerDAO = new TrainerDAOImpl(storage);
    }

    @Test
    public void testSaveTrainer() {
        Trainer trainer = new Trainer(1, 4, 2);
        when(storage.getTrainerMap()).thenReturn(mock(Map.class));
        doNothing().when(storage).getTrainerMap().put(1, trainer);

        int result = trainerDAO.save(trainer);

        assertEquals(1, result);
        verify(storage.getTrainerMap()).put(1, trainer);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveTrainerDuplicateId() {
        Trainer trainer = new Trainer(1, 4, 2);
        Map<Integer, Trainer> trainerMap = mock(Map.class);
        when(storage.getTrainerMap()).thenReturn(trainerMap);
        when(trainerMap.containsKey(1)).thenReturn(true);

        trainerDAO.save(trainer);
    }

    @Test
    public void testGetTrainerById() {
        Trainer trainer = new Trainer(1, 4, 2);
        when(storage.getTrainerMap()).thenReturn(mock(Map.class));
        when(storage.getTrainerMap().get(1)).thenReturn(trainer);

        Trainer result = trainerDAO.getById(1);

        assertNotNull(result);
        assertEquals(trainer, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetTrainerByIdNotFound() {
        when(storage.getTrainerMap()).thenReturn(mock(Map.class));

        trainerDAO.getById(1);
    }

    @Test
    public void testNextAvailableId() {
        when(storage.nextAvailableTrainerId()).thenReturn(1);

        int result = trainerDAO.nextAvailableId();

        assertEquals(1, result);
        verify(storage).nextAvailableTrainerId();
    }
}
