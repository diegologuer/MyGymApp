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
    private TraineeDAO systemUnderTest;


    @Before
    public void setUp() {
        //Arrange
        storage = mock(Storage.class);
        systemUnderTest = new TraineeDAOImpl(storage);
    }

    @Test
    public void givenTrainee_whenSave_thenShouldReturnSavedTraineeId() {
        // Arrange
        int expectedId = 8;
        Trainee trainee = new Trainee(8, new Date(), "Address", 6);
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        // Act
        int actualId = systemUnderTest.save(trainee);

        // Assert
        assertEquals(expectedId, actualId);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTraineeMap_whenGetTraineeById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        // Act and Assert
        systemUnderTest.getById(1);
    }

    @Test
    public void givenTrainee_whenRemoveById_thenShouldReturnRemovedTrainee() {
        // Arrange
        Trainee expectedTrainee = new Trainee(4, new Date(), "Address", 6);
        int traineeId = expectedTrainee.getId();
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());
        systemUnderTest.save(expectedTrainee);

        // Act
        Trainee actualTrainee = systemUnderTest.removeById(traineeId);

        // Assert
        assertEquals(expectedTrainee, actualTrainee);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyTraineeMap_whenRemoveTraineeById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getTraineeMap()).thenReturn(new HashMap<>());

        // Act and Assert
        systemUnderTest.removeById(1);
    }

    @Test
    public void givenNextAvailableTraineeId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        when(storage.nextAvailableTraineeId()).thenReturn(1);

        // Act
        int result = systemUnderTest.nextAvailableId();

        // Assert
        assertEquals(1, result);
        verify(storage).nextAvailableTraineeId();
    }
}
