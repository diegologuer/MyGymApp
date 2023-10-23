package org.spring.repository.TrainingType;

import org.spring.model.TrainingType;

import java.util.List;

public interface TrainingTypeDAO {
    TrainingType getById(int id);
    int nextAvailableId();
}
