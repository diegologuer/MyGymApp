package org.spring.repository.TrainingType;

import org.spring.model.TrainingType;

import java.util.List;

public interface TrainingTypeDAO {
    void save(TrainingType trainingType);
    TrainingType getById(int id);
    List<TrainingType> getAll();
    TrainingType removeById(int id);
    Boolean checkTrainingTypeExistence(String trainingTypeName);
    int nextAvailableId();
}
