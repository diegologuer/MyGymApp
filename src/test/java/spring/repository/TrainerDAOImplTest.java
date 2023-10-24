package spring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.spring.model.Trainer;
import org.spring.repository.trainer.TrainerDAO;
import org.spring.repository.trainer.TrainerDAOImpl;
import org.spring.storage.Storage;

import java.util.HashMap;
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
    public void saveTest() {
        Trainer trainer = new Trainer(1, 3, 2);
        when(storage.getTrainerMap()).thenReturn(new HashMap<>());
        int saveTrainerId = trainerDAO.save(trainer);
        assertEquals(1, saveTrainerId);
    }

    @Test
    public void testGetTrainerById() {
        Trainer trainer = new Trainer(1, 4, 2);
        when(storage.getTrainerMap()).thenReturn(Mockito.<Map>mock(Map.class));
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
