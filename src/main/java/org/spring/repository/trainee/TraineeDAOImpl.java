package org.spring.repository.trainee;

import org.spring.model.Trainee;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Repository
public class TraineeDAOImpl implements TraineeDAO {
    private final Storage storage;
    private static final Logger logger = Logger.getLogger(TraineeDAOImpl.class.getName());

    @Autowired
    public TraineeDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(Trainee trainee) {
        int id = trainee.getId();
        //Save trainee in storage
        storage.getTraineeMap().put(id, trainee);
        logger.info("Saving Trainee...");
        //Check existence of previously saved trainee in the storage
        if (storage.getTraineeMap().containsKey(id)) {
            //Trainee successfully saved
            logger.info("Trainee successfully saved with id: " + id);
            return trainee.getId();
        } else {
            //Throws an exception in case trainee is not found
            throw new RuntimeException("Error saving trainee");
        }
    }

    @Override
    public Trainee getById(int id) {
        logger.info("Searching for trainee in the storage...");
        Trainee trainee = storage.getTraineeMap().get(id);
        if (trainee == null) {
            throw new NoSuchElementException("The given id: " + id + " doesn't match with any trainee in storage");
        }
        logger.info("Trainee found in storage");
        return trainee;
    }

    @Override
    public Trainee removeById(int id) {
        //Search for existence
        logger.info("Searching for trainee in the storage...");
        Trainee trainee = storage.getTraineeMap().get(id);

        //If trainee is not found throw an exception
        if (trainee == null) {
            throw new NoSuchElementException("The given id: " + id + " doesn't match with any trainee in storage");
        }

        //If found, delete it
        logger.info("Deleting trainee...");
        storage.getTraineeMap().remove(id);
        return trainee;
    }

    @Override

    public int nextAvailableId() {
        logger.info("Obtaining next available Id");
        return storage.nextAvailableTraineeId();
    }
}


