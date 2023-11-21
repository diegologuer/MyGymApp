package org.spring.service.trainer;


import org.spring.model.Trainer;
import org.spring.repository.trainer.TrainerDAO;
import org.spring.repository.trainer.TrainerDAOImpl;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class TrainerServiceImpl implements TrainerService {


    private final TrainerDAO trainerDAO;
    private final CredentialsService credentialsService;
    private final TrainingTypeDAO trainingTypeDAO;
    private static final Logger logger = Logger.getLogger(TrainerServiceImpl.class.getName());

    @Autowired
    public TrainerServiceImpl(TrainerDAO trainerDAO, CredentialsService credentialsService,
                              TrainingTypeDAO trainingTypeDAO) {
        this.trainerDAO = trainerDAO;
        this.credentialsService = credentialsService;
        this.trainingTypeDAO = trainingTypeDAO;
    }

    @Override
    public int createTrainer(String name, String lastname, int specialization) {
        //Check is specialization exists
        validateSpecialization(specialization);

        //Generating user, name and lastname are validated in the credentialsService's method
        int userId = credentialsService.createPersonProfile(name, lastname);

        //Assigning available IDs
        int trainerId = trainerDAO.nextAvailableId();

        //Return the ID number of the created Trainee
        return trainerDAO.save(new Trainer(trainerId, userId, specialization));
    }

    @Override
    public Trainer updateTrainer(int trainerId, int specialization) {
        //Check is specialization exists
        validateSpecialization(specialization);

        //Check if specialization id exists
        trainingTypeDAO.getById(specialization);

        //Search for trainer, also validates if exists
        Trainer trainer = trainerDAO.getById(trainerId);

        logger.info("Setting new data...");
        //Set new specialization
        trainer.setSpecialization(specialization);

        //Save trainer
        trainerDAO.save(trainer);
        logger.info("Trainee updated");
        return trainer;
    }

    @Override
    public Trainer getTrainerById(int trainerId) {

        logger.info("Trainee redeemed");
        //return Trainer and also validates if exists
        return trainerDAO.getById(trainerId);
    }

    private void validateSpecialization(int specialization){
        //validates if int is negative
        if(specialization < 1){
            throw new NoSuchElementException("The specialization id: " + specialization +
                    " cannot be a negative number");
        }

        //validates specialization
        trainingTypeDAO.getById(specialization);
    }
}

