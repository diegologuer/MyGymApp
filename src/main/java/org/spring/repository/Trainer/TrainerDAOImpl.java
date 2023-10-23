package org.spring.repository.Trainer;

import org.spring.model.Trainer;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TrainerDAOImpl implements TrainerDAO {
    private Storage storage;
    private static int id = 1;

    @Autowired
    public TrainerDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(Trainer trainer) {
        int id = trainer.getID();
        storage.getTrainerMap().put(id, trainer);
        if (storage.getTrainerMap().containsKey(id)){
            //Trainer successfully saved
            return trainer.getID();
        }
        else{
            //-1 means error saving Trainer
            return -1;
        }
    }

    @Override
    public Trainer getById(int id) {
        return storage.getTrainerMap().get(id);
    }

    @Override
    public List<Trainer> getAll() {
        return new ArrayList<>(storage.getTrainerMap().values());
    }

    @Override
    public void saveAll(List<Trainer> trainers) {
        Map<Integer, Trainer> trainerMap = storage.getTrainerMap();
        for (Trainer trainer : trainers) {
            trainerMap.put(trainer.getID(), trainer);
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
