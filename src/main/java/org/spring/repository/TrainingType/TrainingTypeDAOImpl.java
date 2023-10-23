package org.spring.repository.TrainingType;

import org.spring.model.TrainingType;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingTypeDAOImpl implements TrainingTypeDAO{
    Storage storage;
    private static int id = 1;

    @Autowired
    public TrainingTypeDAOImpl(Storage storage){
        this.storage = storage;
    }

    @Override
    public void save(TrainingType trainingType) {

    }

    @Override
    public TrainingType getById(int id) {
         return storage.getTrainingTypeMap().get(id);
    }

    @Override
    public List<TrainingType> getAll() {
        return null;
    }

    @Override
    public TrainingType removeById(int id) {
        return null;
    }

    @Override
    public Boolean checkTrainingTypeExistence(String trainingTypeName) {
        return null;
    }

    @Override
    public int nextAvailableId() {
        var map = storage.getTrainingTypeMap();
        while(map.containsKey(id)) {
            id++;
        }
        return id;
    }
}
