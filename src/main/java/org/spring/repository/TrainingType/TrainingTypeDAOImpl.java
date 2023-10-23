package org.spring.repository.TrainingType;

import org.spring.model.Trainer;
import org.spring.model.TrainingType;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class TrainingTypeDAOImpl implements TrainingTypeDAO{
    Storage storage;

    @Autowired
    public TrainingTypeDAOImpl(Storage storage){
        this.storage = storage;
    }

    @Override
    public TrainingType getById(int id) {
        System.out.println("Searching for Training Type in storage...");
        TrainingType trainingType = storage.getTrainingTypeMap().get(id);
        if(trainingType == null){
            throw new NoSuchElementException("The given Specialization/Training Type id: " + id + " doesn't match with any training type in storage");
        }
        System.out.println("Training type found in storage");
        return trainingType;
    }

    @Override
    public int nextAvailableId() {
        return storage.nextAvailableTrainingTypeId();
    }
}
