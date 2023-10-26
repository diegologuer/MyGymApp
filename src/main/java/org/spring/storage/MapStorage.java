package org.spring.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
@PropertySource("classpath:application.properties")
public class MapStorage implements Storage {

    private final String traineesFilePath;
    private final String trainersFilePath;
    private final String trainingsFilePath;
    private final String trainingTypesFilePath;
    private final String usersFilePath;

    private final Map<Integer, Trainee> traineeMap = new HashMap<>();
    private final Map<Integer, Trainer> trainerMap = new HashMap<>();
    private final Map<Integer, Training> trainingMap = new HashMap<>();
    private final Map<Integer, TrainingType> trainingTypeMap = new HashMap<>();
    private final Map<Integer, User> userMap = new HashMap<>();

    private static int nextUserId = 1;
    private static int nextTrainingId = 1;
    private static int nextTrainerId = 1;
    private static int nextTraineeId = 1;
    private static int nextTrainingTypeId = 1;

    private static final Logger logger = Logger.getLogger(MapStorage.class.getName());

    @Autowired
    public MapStorage(@Value("${trainees.data.source}") String traineesFilePath, @Value("${trainers.data.source}") String trainersFilePath,
                      @Value("${trainings.data.path}") String trainingsFilePath, @Value("${trainingTypes.data.source}") String trainingTypesFilePath,
                      @Value("${users.data.path}") String usersFilePath) {
        this.traineesFilePath = traineesFilePath;
        this.trainersFilePath = trainersFilePath;
        this.trainingsFilePath = trainingsFilePath;
        this.trainingTypesFilePath = trainingTypesFilePath;
        this.usersFilePath = usersFilePath;
    }

    @PostConstruct
    public void initializeStorageFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Initializing Storage...");

        initializeJsonData(traineesFilePath, objectMapper, Trainee.class, traineeMap, nextTraineeId);
        initializeJsonData(trainersFilePath, objectMapper, Trainer.class, trainerMap, nextTrainerId);
        initializeJsonData(trainingsFilePath, objectMapper, Training.class, trainingMap, nextTrainingId);
        initializeJsonData(trainingTypesFilePath, objectMapper, TrainingType.class, trainingTypeMap, nextTrainingTypeId);
        initializeJsonData(usersFilePath, objectMapper, User.class, userMap, nextUserId);

        logger.info("Storage initialized");
    }

    // Helper method to load and deserialize a JSON file into a map of objects
    public <T> void initializeJsonData(String filePath, ObjectMapper objectMapper, Class<T> valueType, Map<Integer, T> dataMap, int nextId) throws IOException {
        List<T> jsonData = loadJsonList(filePath, objectMapper, valueType);
        for (T data : jsonData) {
            Method method;
            try {
                method = valueType.getMethod("getId");
                Integer id = (Integer) method.invoke(data);
                dataMap.put(id, data);
                if (id >= nextId) {
                    nextId = id + 1;
                }
            } catch (Exception e) {
                logger.warning("Exception occurred while initializing JSON data: " + e.getMessage());
            }
        }
    }
    // Method to load and deserialize a JSON file into a list of objects
    private <T> List<T> loadJsonList(String filePath, ObjectMapper objectMapper, Class<T> valueType) throws IOException {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
        }
        return new ArrayList<>();
    }

    @Override
    public Map<Integer, Trainee> getTraineeMap() {
        logger.info("Retrieving traineeMap");
        return traineeMap;
    }

    @Override
    public Map<Integer, Trainer> getTrainerMap() {
        logger.info("Retrieving trainerMap");
        return trainerMap;
    }

    @Override
    public Map<Integer, Training> getTrainingMap() {
        logger.info("Retrieving trainingMap");
        return trainingMap;
    }

    @Override
    public Map<Integer, TrainingType> getTrainingTypeMap() {
        logger.info("Retrieving trainingTypeMap");
        return trainingTypeMap;
    }

    @Override
    public Map<Integer, User> getUserMap() {
        logger.info("Retrieving userMap");
        return userMap;
    }

    public int nextAvailableUserId() {
        return nextUserId++;
    }

    public int nextAvailableTrainingId() {
        return nextTrainingId++;
    }

    public int nextAvailableTrainerId() {
        return nextTrainerId++;
    }

    public int nextAvailableTraineeId() {
        return nextTraineeId++;
    }

    public int nextAvailableTrainingTypeId() {
        return nextTrainingTypeId++;
    }


}
