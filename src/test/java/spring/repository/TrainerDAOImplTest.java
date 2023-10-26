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
        // Arrange
        storage = mock(Storage.class);
        trainerDAO = new TrainerDAOImpl(storage);
    }

    @Test
    public void givenTrainer_whenSave_thenShouldReturnSavedTrainerId() {
        // Arrange
        Trainer trainer = new Trainer(1, 3, 2);
        when(storage.getTrainerMap()).thenReturn(new HashMap<>());

        // Act
        int saveTrainerId = trainerDAO.save(trainer);

        // Assert
        assertEquals(1, saveTrainerId);
    }

    @Test
    public void givenTrainerMapWithTrainer_whenGetTrainerById_thenShouldReturnTrainer() {
        // Arrange
        Trainer trainer = new Trainer(1, 4, 2);
        when(storage.getTrainerMap()).thenReturn(Mockito.<Map>mock(Map.class));
        when(storage.getTrainerMap().get(1)).thenReturn(trainer);

        // Act
        Trainer result = trainerDAO.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals(trainer, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTrainerMap_whenGetTrainerById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTrainerMap()).thenReturn(mock(Map.class));

        // Act and Assert
        trainerDAO.getById(1);
    }

    @Test
    public void givenNextAvailableTrainerId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        when(storage.nextAvailableTrainerId()).thenReturn(1);

        // Act
        int result = trainerDAO.nextAvailableId();

        // Assert
        assertEquals(1, result);
        verify(storage).nextAvailableTrainerId();
    }
}
