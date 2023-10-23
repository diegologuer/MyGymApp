package org.spring.repository.Trainee;

import org.spring.model.Trainee;
import java.util.List;

public interface TraineeDAO {
    int save(Trainee trainee);
    Trainee getById(int id);
    Trainee removeById(int id);
    int nextAvailableId();
}