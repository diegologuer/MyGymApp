package org.spring.service.trainer;


import org.spring.model.Trainer;
import org.spring.model.User;
import org.spring.repository.trainer.TrainerDAOImpl;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TrainerServiceImpl implements TrainerService {


    private final TrainerDAOImpl trainerDAOImpl;
    private final CredentialsService credentialsService;
    private final UserDAOImpl userDAOImpl;
    private final TrainingTypeDAO trainingTypeDAO;
    private static final Logger logger = Logger.getLogger(TrainerServiceImpl.class.getName());

    @Autowired
    public TrainerServiceImpl(TrainerDAOImpl trainerDAOImpl, CredentialsService credentialsService,
                              UserDAOImpl userDAOImpl, TrainingTypeDAO trainingTypeDAO) {
        this.trainerDAOImpl = trainerDAOImpl;
        this.credentialsService = credentialsService;
        this.userDAOImpl = userDAOImpl;
        this.trainingTypeDAO = trainingTypeDAO;
    }

    @Override
    public int createTrainer(String name, String lastname, int specialization) {

        //Generating user
        int userId = credentialsService.createPersonProfile(name, lastname);

        //Assigning available IDs
        int trainerId = trainerDAOImpl.nextAvailableId();

        //Return the ID number of the created Trainee
        return trainerDAOImpl.save(new Trainer(trainerId, userId, specialization));
    }

    @Override
    public Trainer updateTrainer(int trainerId, int specialization) {
        //Check if specialization id exists
        trainingTypeDAO.getById(specialization);

        //Search fot trainer
        Trainer trainer = trainerDAOImpl.getById(trainerId);

        logger.info("Setting new data...");
        //Set new specialization
        trainer.setSpecialization(specialization);

        //Save trainer
        trainerDAOImpl.save(trainer);
        logger.info("Trainee updated");
        return trainer;
    }

    @Override
    public Trainer getTrainerById(int trainerId) {

        logger.info("Trainee redeemed");
        return trainerDAOImpl.getById(trainerId);
    }
}

