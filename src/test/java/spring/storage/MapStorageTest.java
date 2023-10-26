package spring.storage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;

import org.mockito.MockitoAnnotations;
import org.spring.model.*;
import org.spring.storage.MapStorage;

import java.util.Map;

public class MapStorageTest {

    @InjectMocks
    private MapStorage systemUnderTest;

    @Before
    public void init() {
        // Initialize the Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTraineeMap() {
        // Act
        Map<Integer, Trainee> actualTraineeMap = systemUnderTest.getTraineeMap();

        // Assert
        assertNotNull(actualTraineeMap);
    }

    @Test
    public void testGetTrainerMap() {
        // Act
        Map<Integer, Trainer> actualTrainerMap = systemUnderTest.getTrainerMap();

        // Assert
        assertNotNull(actualTrainerMap);
    }

    @Test
    public void testGetTrainingMap() {
        // Act
        Map<Integer, Training> actualTrainingMap = systemUnderTest.getTrainingMap();

        // Assert
        assertNotNull(actualTrainingMap);
    }

    @Test
    public void testGetTrainingTypeMap() {
        // Act
        Map<Integer, TrainingType> actualTrainingTypeMap = systemUnderTest.getTrainingTypeMap();

        // Assert
        assertNotNull(actualTrainingTypeMap);
    }

    @Test
    public void testGetUserMap() {
        // Act
        Map<Integer, User> actualUserMap = systemUnderTest.getUserMap();

        // Assert
        assertNotNull(actualUserMap);
    }

    @Test
    public void testNextAvailableUserId() {
        // Act
        int nextUserId = systemUnderTest.nextAvailableUserId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextUserId);
        int nextUserId2 = systemUnderTest.nextAvailableUserId();
        assertEquals(2, nextUserId2);
    }

    @Test
    public void testNextAvailableTrainingId() {
        // Act
        int nextTrainingId = systemUnderTest.nextAvailableTrainingId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainingId);
        int nextTrainingId2 = systemUnderTest.nextAvailableTrainingId();
        assertEquals(2, nextTrainingId2);
    }

    @Test
    public void testNextAvailableTrainerId() {
        // Act
        int nextTrainerId = systemUnderTest.nextAvailableTrainerId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainerId);
        int nextTrainerId2 = systemUnderTest.nextAvailableTrainerId();
        assertEquals(2, nextTrainerId2);
    }

    @Test
    public void testNextAvailableTraineeId() {
        // Act
        int nextTraineeId = systemUnderTest.nextAvailableTraineeId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTraineeId);
        int nextTraineeId2 = systemUnderTest.nextAvailableTraineeId();
        assertEquals(2, nextTraineeId2);
    }

    @Test
    public void testNextAvailableTrainingTypeId() {
        // Act
        int nextTrainingTypeId = systemUnderTest.nextAvailableTrainingTypeId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainingTypeId);
        int nextTrainingTypeId2 = systemUnderTest.nextAvailableTrainingTypeId();
        assertEquals(2, nextTrainingTypeId2);
    }
}



