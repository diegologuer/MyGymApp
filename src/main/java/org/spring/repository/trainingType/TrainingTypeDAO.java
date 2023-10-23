package org.spring.repository.trainingType;

import org.spring.model.TrainingType;

public interface TrainingTypeDAO {
    TrainingType getById(int id);

    int nextAvailableId();
}
