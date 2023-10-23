package org.spring.repository.Training;

import org.spring.model.Training;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TrainingDAOImpl implements TrainingDAO {
    private Storage storage;
    private static int id = 1;

    @Autowired
    public TrainingDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(Training training) {
        int id = training.getid();
        storage.getTrainingMap().put(id, training);
        if (storage.getTrainingMap().containsKey(id)){
            //Training successfully saved
            return training.getid();
        }
        else{
            //-1 means error saving Training
            return -1;
        }
    }

    @Override
    public Training getById(int id) {
        return storage.getTrainingMap().get(id);
    }

    @Override
    public List<Training> getAll() {
        return new ArrayList<>(storage.getTrainingMap().values());
    }

    @Override
    public void saveAll(List<Training> trainings) {
        Map<Integer, Training> trainingMap = storage.getTrainingMap();
        for (Training training : trainings) {
            trainingMap.put(training.getid(), training);
        }
    }

    @Override
    public int nextAvailableId() {
        var map = storage.getTrainingMap();
        while(map.containsKey(id)) {
            id++;
        }
        return id;
    }
}
