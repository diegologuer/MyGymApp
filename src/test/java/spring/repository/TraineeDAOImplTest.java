package spring.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.Trainee;
import org.spring.repository.trainee.TraineeDAO;
import org.spring.repository.trainee.TraineeDAOImpl;
import org.spring.storage.Storage;

import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class TraineeDAOImplTest {

    private Storage storage;
    private TraineeDAO traineeDAO;

    @Before
    public void setUp() {
        //Arrange
        storage = mock(Storage.class);
        traineeDAO = new TraineeDAOImpl(storage);
    }

    @Test
    public void givenTrainee_whenSave_thenShouldReturnSavedTraineeId() {
        // Arrange
        Trainee trainee = new Trainee(8, new Date(), "Address", 6);
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        // Act
        int saveTraineeId = traineeDAO.save(trainee);

        // Assert
        assertEquals(8, saveTraineeId);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTraineeMap_whenGetTraineeById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        // Act and Assert
        traineeDAO.getById(1);
    }

    @Test
    public void givenTrainee_whenRemoveById_thenShouldReturnRemovedTrainee() {
        // Arrange
        Trainee trainee = new Trainee(4, new Date(), "Address", 6);
        int traineeId = trainee.getId();
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());
        traineeDAO.save(trainee);

        // Act
        Trainee removedTrainee = traineeDAO.removeById(traineeId);

        // Assert
        assertEquals(trainee, removedTrainee);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTraineeMap_whenRemoveTraineeById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        // Act and Assert
        traineeDAO.removeById(1);
    }

    @Test
    public void givenNextAvailableTraineeId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        when(storage.nextAvailableTraineeId()).thenReturn(1);

        // Act
        int result = traineeDAO.nextAvailableId();

        // Assert
        assertEquals(1, result);
        verify(storage).nextAvailableTraineeId();
    }
}
