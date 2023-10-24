package org.spring.repository.training;

import org.spring.model.Training;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.NoSuchElementException;

@Repository
public class TrainingDAOImpl implements TrainingDAO {
    private Storage storage;

    @Autowired
    public TrainingDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(Training training) {
        int id = training.getid();
        //Save training in storage
        storage.getTrainingMap().put(id, training);
        System.out.println("Saving Training...");
        //Check existence of previously saved training in the storage
        if (storage.getTrainingMap().containsKey(id)) {
            //Training successfully saved
            System.out.println("Training successfully saved with id: " + id);
            return training.getid();
        } else {
            //-1 means error saving Training
            System.out.println("Error saving Training");
            return -1;
        }
    }

    @Override
    public Training getById(int id) {
        System.out.println("Searching for Training in storage...");
        Training training = storage.getTrainingMap().get(id);
        if (training == null) {
            throw new NoSuchElementException("The given id: " + id + " doesn't match with any training in storage");
        }
        System.out.println("Training found in storage");
        return training;
    }

    @Override
    public int nextAvailableId() {
        return storage.nextAvailableTrainingId();
    }
}
