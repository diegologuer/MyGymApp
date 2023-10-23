package org.spring.service.training;

import org.spring.model.Training;

import java.util.Date;

public interface TrainingService {
    int createTraining(String trainingName, Date trainingDate, int traineeId,
                       int trainingTypeId, int trainingDuration, int trainerId);

    Training getTrainingById(int trainingId);
}
