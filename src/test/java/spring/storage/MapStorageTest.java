package spring.storage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.MockitoAnnotations;
import org.spring.model.*;
import org.spring.storage.MapStorage;

import java.util.Map;

public class MapStorageTest {

    @InjectMocks
    private MapStorage mapStorage;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        // Initialize the Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTraineeMap() {
        // Act
        Map<Integer, Trainee> traineeMap = mapStorage.getTraineeMap();

        // Assert
        assertNotNull(traineeMap);
    }

    @Test
    public void testGetTrainerMap() {
        // Act
        Map<Integer, Trainer> trainerMap = mapStorage.getTrainerMap();

        // Assert
        assertNotNull(trainerMap);
    }

    @Test
    public void testGetTrainingMap() {
        // Act
        Map<Integer, Training> trainingMap = mapStorage.getTrainingMap();

        // Assert
        assertNotNull(trainingMap);
    }

    @Test
    public void testGetTrainingTypeMap() {
        // Act
        Map<Integer, TrainingType> trainingTypeMap = mapStorage.getTrainingTypeMap();

        // Assert
        assertNotNull(trainingTypeMap);
    }

    @Test
    public void testGetUserMap() {
        // Act
        Map<Integer, User> userMap = mapStorage.getUserMap();

        // Assert
        assertNotNull(userMap);
    }

    @Test
    public void testNextAvailableUserId() {
        // Act
        int nextUserId = mapStorage.nextAvailableUserId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextUserId);
        int nextUserId2 = mapStorage.nextAvailableUserId();
        assertEquals(2, nextUserId2);
    }

    @Test
    public void testNextAvailableTrainingId() {
        // Act
        int nextTrainingId = mapStorage.nextAvailableTrainingId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainingId);
        int nextTrainingId2 = mapStorage.nextAvailableTrainingId();
        assertEquals(2, nextTrainingId2);
    }

    @Test
    public void testNextAvailableTrainerId() {
        // Act
        int nextTrainerId = mapStorage.nextAvailableTrainerId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainerId);
        int nextTrainerId2 = mapStorage.nextAvailableTrainerId();
        assertEquals(2, nextTrainerId2);
    }

    @Test
    public void testNextAvailableTraineeId() {
        // Act
        int nextTraineeId = mapStorage.nextAvailableTraineeId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTraineeId);
        int nextTraineeId2 = mapStorage.nextAvailableTraineeId();
        assertEquals(2, nextTraineeId2);
    }

    @Test
    public void testNextAvailableTrainingTypeId() {
        // Act
        int nextTrainingTypeId = mapStorage.nextAvailableTrainingTypeId();

        // Assert
        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainingTypeId);
        int nextTrainingTypeId2 = mapStorage.nextAvailableTrainingTypeId();
        assertEquals(2, nextTrainingTypeId2);
    }
}



