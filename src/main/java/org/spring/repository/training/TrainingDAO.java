package org.spring.repository.training;

import org.spring.model.Training;

public interface TrainingDAO {
    int save(Training training);

    Training getById(int id);

    int nextAvailableId();
}