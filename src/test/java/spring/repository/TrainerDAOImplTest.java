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

@SuppressWarnings("rawtypes")
public class TrainerDAOImplTest {
    private Storage storage;
    private TrainerDAO systemUnderTest;

    @Before
    public void setUp() {
        // Arrange
        storage = mock(Storage.class);
        systemUnderTest = new TrainerDAOImpl(storage);
    }

    @Test
    public void givenTrainer_whenSave_thenShouldReturnSavedTrainerId() {
        // Arrange
        int expectedTrainerId = 1;
        Trainer trainer = new Trainer(1, 3, 2);
        when(storage.getTrainerMap()).thenReturn(new HashMap<>());

        // Act
        int actualTrainerId = systemUnderTest.save(trainer);

        // Assert
        assertEquals(expectedTrainerId, actualTrainerId);
    }

    @Test
    public void givenTrainerMapWithTrainer_whenGetTrainerById_thenShouldReturnTrainer() {
        // Arrange
        Trainer expectedTrainer = new Trainer(1, 4, 2);
        //noinspection rawtypes
        when(storage.getTrainerMap()).thenReturn(Mockito.<Map>mock(Map.class));
        when(storage.getTrainerMap().get(1)).thenReturn(expectedTrainer);

        // Act
        Trainer actualTrainer = systemUnderTest.getById(1);

        // Assert
        assertNotNull(actualTrainer);
        assertEquals(expectedTrainer, actualTrainer);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTrainerMap_whenGetTrainerById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTrainerMap()).thenReturn(mock(Map.class));

        // Act and Assert
        systemUnderTest.getById(1);
    }

    @Test
    public void givenNextAvailableTrainerId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        int expectedAvailableId = 1;
        when(storage.nextAvailableTrainerId()).thenReturn(1);

        // Act
        int actualAvailableId = systemUnderTest.nextAvailableId();

        // Assert
        assertEquals(expectedAvailableId, actualAvailableId);
        verify(storage).nextAvailableTrainerId();
    }
}
