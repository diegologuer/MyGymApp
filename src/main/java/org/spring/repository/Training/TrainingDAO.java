package org.spring.repository.Training;

import org.spring.model.Training;

import java.util.List;

public interface TrainingDAO {
    int save(Training training);
    Training getById(int id);
    List<Training> getAll();
    void saveAll(List<Training> trainings);
    int nextAvailableId();
}