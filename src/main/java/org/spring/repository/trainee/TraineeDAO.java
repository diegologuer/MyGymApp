package org.spring.repository.trainee;

import org.spring.model.Trainee;

public interface TraineeDAO {
    int save(Trainee trainee);

    Trainee getById(int id);

    Trainee removeById(int id);

    int nextAvailableId();
}