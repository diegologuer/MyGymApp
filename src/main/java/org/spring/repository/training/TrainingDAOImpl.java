package org.spring.repository.training;

import org.spring.model.Training;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Repository
public class TrainingDAOImpl implements TrainingDAO {
    private final Storage storage;
    private static final Logger logger = Logger.getLogger(TrainingDAOImpl.class.getName());

    @Autowired
    public TrainingDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(Training training) {
        int id = training.getId();
        //Save training in storage
        storage.getTrainingMap().put(id, training);
        logger.info("Saving Training...");
        //Check existence of previously saved training in the storage
        if (storage.getTrainingMap().containsKey(id)) {
            //Training successfully saved
            logger.info("Training successfully saved with id: " + id);
            return training.getId();
        } else {
            throw new RuntimeException("Error saving Training");
        }
    }

    @Override
    public Training getById(int id) {
        //Search for existence
        logger.info("Searching for Training in storage...");
        Training training = storage.getTrainingMap().get(id);

        //If trainee is not found throw an exception
        if (training == null) {
            throw new NoSuchElementException("The given id: " + id + " doesn't match with any training in storage");
        }

        //If found, delete it
        logger.info("Training found in storage");
        return training;
    }

    @Override
    public int nextAvailableId() {
        logger.info("Obtaining next available Id");
        return storage.nextAvailableTrainingId();
    }
}
