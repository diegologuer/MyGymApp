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
        // Load and map Trainee objects from the JSON file
        List<Trainee> trainees = loadJsonList(traineesFilePath, objectMapper, Trainee.class);
        for (Trainee trainee : trainees) {
            traineeMap.put(trainee.getID(), trainee);
            if (trainee.getID() >= nextTraineeId) {
                nextTraineeId = trainee.getID() + 1;
            }
        }

        // Load and map Trainer objects from the JSON file
        List<Trainer> trainers = loadJsonList(trainersFilePath, objectMapper, Trainer.class);
        for (Trainer trainer : trainers) {
            trainerMap.put(trainer.getID(), trainer);
            if (trainer.getID() >= nextTrainerId) {
                nextTrainerId = trainer.getID() + 1;
            }
        }

        // Load and map Training objects from the JSON file
        List<Training> trainings = loadJsonList(trainingsFilePath, objectMapper, Training.class);
        for (Training training : trainings) {
            trainingMap.put(training.getid(), training);
            if (training.getid() >= nextTrainingId) {
                nextTrainingId = training.getid() + 1;
            }
        }

        // Load and map TrainingType objects from the JSON file
        List<TrainingType> trainingTypes = loadJsonList(trainingTypesFilePath, objectMapper, TrainingType.class);
        for (TrainingType trainingType : trainingTypes) {
            trainingTypeMap.put(trainingType.getId(), trainingType);
            if (trainingType.getId() >= nextTrainingTypeId) {
                nextTrainingTypeId = trainingType.getId() + 1;
            }
        }

        // Load and map User objects from the JSON file
        List<User> users = loadJsonList(usersFilePath, objectMapper, User.class);
        for (User user : users) {
            userMap.put(user.getId(), user);
            if (user.getId() >= nextUserId) {
                nextUserId = user.getId() + 1;
            }
        }
        logger.info("Storage initialized");
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
