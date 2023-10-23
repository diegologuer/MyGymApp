package org.spring.storage;

import org.spring.model.*;

import java.util.Map;

public interface Storage {
    Map<Integer, Trainee> getTraineeMap();
    Map<Integer, Trainer> getTrainerMap();
    Map<Integer, Training> getTrainingMap();
    Map<Integer, TrainingType> getTrainingTypeMap();
    Map<Integer, User> getUserMap();


}

