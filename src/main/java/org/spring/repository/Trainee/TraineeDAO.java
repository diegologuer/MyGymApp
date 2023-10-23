package org.spring.repository.Trainee;

import org.spring.model.Trainee;
import java.util.List;

public interface TraineeDAO {
    int save(Trainee trainee);
    Trainee getById(int id);
    List<Trainee> getAll();
    Trainee removeById(int id);
    void saveAll(List<Trainee> trainees);
    int nextAvailableId();
}