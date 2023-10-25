package org.spring.repository.trainingType;

import org.spring.model.TrainingType;
import org.spring.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Repository
public class TrainingTypeDAOImpl implements TrainingTypeDAO {
    final Storage storage;
    private static final Logger logger = Logger.getLogger(TrainingTypeDAOImpl.class.getName());

    @Autowired
    public TrainingTypeDAOImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public TrainingType getById(int id) {
        logger.info("Searching for Training Type in storage...");
        TrainingType trainingType = storage.getTrainingTypeMap().get(id);
        if (trainingType == null) {
            throw new NoSuchElementException("The given Specialization/Training Type id: " + id + " doesn't match with any training type in storage");
        }
        logger.info("Training type found in storage");
        return trainingType;
    }

    @Override
    public int nextAvailableId() {
        logger.info("Obtaining next available Id");
        return storage.nextAvailableTrainingTypeId();
    }
}
