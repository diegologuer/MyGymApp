package org.spring.storage;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
class MapStorageTest {

    @InjectMocks
    MapStorage sut;

    @Test
    void givenValidArgs_whenInitializingJsonData_DataIsDeserialized() throws IOException {
        //Arrange
        String filePath = "src/main/resources/initData/trainees.json";
        ObjectMapper objectMapper = new ObjectMapper();
        Class<Trainee> valueType = Trainee.class;
        Map<Integer, Trainee> dataMap = new HashMap<>();
        int nextId = 1;

        //Act
        sut.initializeJsonData(filePath, objectMapper, valueType, dataMap, nextId);

        //Assert
        assertFalse(dataMap.isEmpty());
    }

    @Test
    void givenInvalidPath_whenInitializingJsonData_DataIsNotDeserialized() throws IOException {
        //Arrange
        String filePath = "not a valid path";
        ObjectMapper objectMapper = new ObjectMapper();
        Class<Trainee> valueType = Trainee.class;
        Map<Integer, Trainee> dataMap = new HashMap<>();
        int nextId = 1;

        //Act
        sut.initializeJsonData(filePath, objectMapper, valueType, dataMap, nextId);

        //Assert
        assertTrue(dataMap.isEmpty());
    }

    @Test
    void testNextAvailableIds() {
        int nextUserIdBefore = sut.nextAvailableUserId();
        int nextTrainingIdBefore = sut.nextAvailableTrainingId();
        int nextTrainerIdBefore = sut.nextAvailableTrainerId();
        int nextTraineeIdBefore = sut.nextAvailableTraineeId();
        int nextTrainingTypeIdBefore = sut.nextAvailableTrainingTypeId();

        assertEquals(nextUserIdBefore + 1, sut.nextAvailableUserId());
        assertEquals(nextTrainingIdBefore + 1, sut.nextAvailableTrainingId());
        assertEquals(nextTrainerIdBefore + 1, sut.nextAvailableTrainerId());
        assertEquals(nextTraineeIdBefore + 1, sut.nextAvailableTraineeId());
        assertEquals(nextTrainingTypeIdBefore + 1, sut.nextAvailableTrainingTypeId());
    }


}