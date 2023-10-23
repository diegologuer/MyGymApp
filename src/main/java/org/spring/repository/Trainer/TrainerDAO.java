package org.spring.repository.Trainer;

import org.spring.model.Trainer;
import java.util.List;

public interface TrainerDAO {
    int save(Trainer trainer);
    Trainer getById(int id);
    int nextAvailableId();
}