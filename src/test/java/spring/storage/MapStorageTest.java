package spring.storage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring.model.Trainee;
import org.spring.storage.MapStorage;

import java.io.File;
import java.util.Map;

public class MapStorageTest {

    private MapStorage mapStorage;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = mock(ObjectMapper.class);
        mapStorage = new MapStorage("trainees.json", "trainers.json", "trainings.json", "trainingTypes.json", "users.json");
    }


    @Test
    public void testNextAvailableUserId() {
        int nextUserId = mapStorage.nextAvailableUserId();

        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextUserId);
        int nextUserId2 = mapStorage.nextAvailableUserId();
        assertEquals(2, nextUserId2);
    }

    @Test
    public void testNextAvailableTrainingId() {
        int nextTrainingId = mapStorage.nextAvailableTrainingId();

        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainingId);
        int nextTrainingId2 = mapStorage.nextAvailableTrainingId();
        assertEquals(2, nextTrainingId2);
    }

    @Test
    public void testNextAvailableTrainerId() {
        int nextTrainerId = mapStorage.nextAvailableTrainerId();

        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainerId);
        int nextTrainerId2 = mapStorage.nextAvailableTrainerId();
        assertEquals(2, nextTrainerId2);
    }

    @Test
    public void testNextAvailableTraineeId() {
        int nextTraineeId = mapStorage.nextAvailableTraineeId();

        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTraineeId);
        int nextTraineeId2 = mapStorage.nextAvailableTraineeId();
        assertEquals(2, nextTraineeId2);
    }

    @Test
    public void testNextAvailableTrainingTypeId() {
        int nextTrainingTypeId = mapStorage.nextAvailableTrainingTypeId();

        // Verify that the returned ID is as expected and incremented
        assertEquals(1, nextTrainingTypeId);
        int nextTrainingTypeId2 = mapStorage.nextAvailableTrainingTypeId();
        assertEquals(2, nextTrainingTypeId2);
    }
}
