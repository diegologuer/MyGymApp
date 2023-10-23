package org.spring.repository.trainer;

import org.spring.model.Trainer;

public interface TrainerDAO {
    int save(Trainer trainer);

    Trainer getById(int id);

    int nextAvailableId();
}