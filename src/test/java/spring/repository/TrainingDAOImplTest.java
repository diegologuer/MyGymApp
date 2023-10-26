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
    private TrainingDAO systemUnderTest;

    @Before
    public void setUp() {
        // Arrange
        storage = mock(Storage.class);
        systemUnderTest = new TrainingDAOImpl(storage);
    }

    @Test
    public void givenTraining_whenSave_thenShouldReturnSavedTrainingId() {
        // Arrange
        int expectedTrainingId = 8;
        Training training = new Training(8, "Running", new Date(), 1, 1, 20, 1);
        when(storage.getTrainingMap()).thenReturn(new HashMap<>());

        // Act
        int actualTrainingId = systemUnderTest.save(training);

        // Assert
        assertEquals(expectedTrainingId, actualTrainingId);
    }

    @Test
    public void givenTrainingMapWithTraining_whenGetTrainingById_thenShouldReturnTraining() {
        // Arrange
        Training expectedTraining = new Training(1, "Cardio Workout", new Date(), 1, 1, 60, 1);
        when(storage.getTrainingMap()).thenReturn(mock(Map.class));
        when(storage.getTrainingMap().get(1)).thenReturn(expectedTraining);

        // Act
        Training actualTraining = systemUnderTest.getById(1);

        // Assert
        assertNotNull(actualTraining);
        assertEquals(expectedTraining, actualTraining);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTrainingMap_whenGetTrainingById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTrainingMap()).thenReturn(mock(Map.class));

        // Act and Assert
        systemUnderTest.getById(1);
    }

    @Test
    public void givenNextAvailableTrainingId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        int expectedAvailableId = 1;
        when(storage.nextAvailableTrainingId()).thenReturn(1);

        // Act
        int actualAvailableId = systemUnderTest.nextAvailableId();

        // Assert
        assertEquals(expectedAvailableId, actualAvailableId);
        verify(storage).nextAvailableTrainingId();
    }
}
