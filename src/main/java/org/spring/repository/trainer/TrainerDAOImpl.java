package org.spring.repository.trainer;

import org.spring.model.Trainer;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Repository
public class TrainerDAOImpl implements TrainerDAO {
    private final Storage storage;
    private static final Logger logger = Logger.getLogger(TrainerDAOImpl.class.getName());

    @Autowired
    public TrainerDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public int save(Trainer trainer) {
        int id = trainer.getID();
        //Save trainer in storage
        storage.getTrainerMap().put(id, trainer);
        logger.info("Saving Trainer...");
        //Check existence of previously saved trainer in the storage
        if (storage.getTrainerMap().containsKey(id)) {
            //Trainer successfully saved
            logger.info("Trainer successfully saved with id: " + id);
            return trainer.getID();
        } else {
            //Throws an exception in case trainee is not found
            throw new RuntimeException("Error saving trainee");
        }
    }

    @Override
    public Trainer getById(int id) {
        //Search for existence
        logger.info("Searching for Trainer in storage...");
        Trainer trainer = storage.getTrainerMap().get(id);

        //If trainee is not found throw an exception
        if (trainer == null) {
            throw new NoSuchElementException("The given id: " + id + " doesn't match with any trainer in storage");
        }

        //If found, delete it
        logger.info("Trainer found in storage");
        return trainer;
    }

    @Override

    public int nextAvailableId() {
        logger.info("Obtaining next available Id");
        return storage.nextAvailableTrainerId();
    }
}
