package org.spring.storage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource("classpath:application.properties")
public class MapStorage implements Storage {

    private final String jsonFilePath;
    private Map<Integer, Trainee> traineeMap = new HashMap<>();
    private Map<Integer, Trainer> trainerMap = new HashMap<>();
    private Map<Integer, Training> trainingMap = new HashMap<>();
    private Map<Integer, TrainingType> trainingTypeMap = new HashMap<>();
    private Map<Integer, User> userMap = new HashMap<>();


    private static int nextUserId = 1;
    private static int nextTrainingId = 1;
    private static int nextTrainerId = 1;
    private static int nextTraineeId = 1;
    private static int nextTrainingTypeId = 1;

    @Autowired
    public MapStorage(@Value("${json.file.path}") String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @PostConstruct
    public void initializeStorageFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(jsonFilePath);
        JsonNode jsonNode = objectMapper.readTree(jsonFile);

        for (JsonNode element : jsonNode) {
            String type = element.get("type").asText();
            JsonNode data = element.get("data");

            if ("trainee".equals(type)) {
                Trainee trainee = objectMapper.treeToValue((data), Trainee.class);
                traineeMap.put(trainee.getID(), trainee);
                nextTraineeId++;
            } else if ("trainer".equals(type)) {
                Trainer trainer = objectMapper.treeToValue((data), Trainer.class);
                trainerMap.put(trainer.getID(), trainer);
                nextTrainerId++;
            } else if ("training".equals(type)) {
                Training training = objectMapper.treeToValue((data), Training.class);
                trainingMap.put(training.getid(), training);
                nextTrainingId++;
            } else if ("trainingType".equals(type)) {
                TrainingType trainingType = objectMapper.treeToValue((data), TrainingType.class);
                trainingTypeMap.put(trainingType.getId(), trainingType);
                nextTrainingTypeId++;
            } else if ("user".equals(type)) {
                User user = objectMapper.treeToValue((data), User.class);
                userMap.put(user.getId(), user);
                nextUserId++;
            }
        }
    }

    @Override
    public Map<Integer, Trainee> getTraineeMap() {
        return traineeMap;
    }

    @Override
    public Map<Integer, Trainer> getTrainerMap() {
        return trainerMap;
    }

    @Override
    public Map<Integer, Training> getTrainingMap() {
        return trainingMap;
    }

    @Override
    public Map<Integer, TrainingType> getTrainingTypeMap() {
        return trainingTypeMap;
    }

    @Override
    public Map<Integer, User> getUserMap() {
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
