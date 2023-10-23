package org.spring.repository.Trainer;

import org.spring.model.Trainer;
import java.util.List;

public interface TrainerDAO {
    int save(Trainer trainer);
    Trainer getById(int id);
    List<Trainer> getAll();
    void saveAll(List<Trainer> trainers);
    int nextAvailableId();
}