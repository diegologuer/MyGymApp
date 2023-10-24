package spring.storage;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.MockitoAnnotations;
import org.spring.storage.MapStorage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapStorageTest {

    @InjectMocks
    private MapStorage mapStorage;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetTraineeMap() {
        assertNotNull(mapStorage.getTraineeMap());
    }

    @Test
    public void testGetTrainerMap() {
        assertNotNull(mapStorage.getTrainerMap());
    }

    @Test
    public void testGetTrainingMap() {
        assertNotNull(mapStorage.getTrainingMap());
    }

    @Test
    public void testGetTrainingTypeMap() {
        assertNotNull(mapStorage.getTrainingTypeMap());
    }

    @Test
    public void testGetUserMap() {
        assertNotNull(mapStorage.getUserMap());
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
    private <T> List<T> loadJsonList(String filePath, ObjectMapper objectMapper, Class<T> valueType) throws IOException {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
        }
        return new ArrayList<>();
    }


}



