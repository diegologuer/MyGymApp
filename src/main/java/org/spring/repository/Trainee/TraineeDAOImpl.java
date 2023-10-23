package org.spring.repository.Trainee;

import org.spring.model.Trainee;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TraineeDAOImpl implements TraineeDAO {
    private Storage storage;
    private static int id = 1;

    @Autowired
    public TraineeDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(Trainee trainee) {
        int id = trainee.getID();
        storage.getTraineeMap().put(id, trainee);
        if (storage.getTraineeMap().containsKey(id)){
            //Trainee successfully saved
            return trainee.getID();
        }
        else{
            //-1 means error saving Trainee
            return -1;
        }
    }

    @Override
    public Trainee getById(int id) {
        return storage.getTraineeMap().get(id);
    }

    @Override
    public List<Trainee> getAll() {
        return new ArrayList<>(storage.getTraineeMap().values());
    }

    @Override
    public Trainee removeById(int id) {
        Trainee trainee = storage.getTraineeMap().get(id);
        storage.getTraineeMap().remove(id);
        return trainee;
    }

    @Override
    public void saveAll(List<Trainee> trainees) {
        Map<Integer, Trainee> traineeMap = storage.getTraineeMap();
        for (Trainee trainee : trainees) {
            traineeMap.put(trainee.getID(), trainee);
        }
    }

    @Override
    public int nextAvailableId() {
        var map = storage.getTraineeMap();
        while(map.containsKey(id)) {
            id++;
        }
        return id;
    }
}


