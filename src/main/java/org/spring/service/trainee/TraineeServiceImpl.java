package org.spring.service.trainee;

import org.spring.model.Trainee;
import org.spring.repository.trainee.TraineeDAOImpl;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeDAOImpl traineeDAOImpl;
    private final CredentialsService credentialsService;
    private final UserDAOImpl userDAOImpl;
    private static final Logger logger = Logger.getLogger(TraineeServiceImpl.class.getName());

    @Autowired
    public TraineeServiceImpl(TraineeDAOImpl traineeDAOImpl, CredentialsService credentialsService, UserDAOImpl userDAOImpl) {
        this.traineeDAOImpl = traineeDAOImpl;
        this.credentialsService = credentialsService;
        this.userDAOImpl = userDAOImpl;
    }

    @Override
    public int createTrainee(String name, String lastname, Date dateOfBirth, String address) {

        //Generating user
        int userId = credentialsService.createPersonProfile(name, lastname);

        //Assigning available IDs
        int traineeId = traineeDAOImpl.nextAvailableId();

        //Return the ID number of the created Trainee
        return traineeDAOImpl.save(new Trainee(traineeId, dateOfBirth, address, userId));
    }

    @Override
    public Trainee updateTrainee(int traineeId, Date dateOfBirth, String address) {

        //Check trainee existence
        Trainee trainee = traineeDAOImpl.getById(traineeId);

        logger.info("Setting new data...");
        //Set data
        trainee.setAddress(address);
        trainee.setDateOfBirth(dateOfBirth);

        //Save trainee to replace previous
        traineeDAOImpl.save(trainee);
        logger.info("Trainee updated");
        return trainee;
    }

    @Override
    public Trainee deleteTrainee(int traineeId) {
        //Search for the trainee
        Trainee trainee = traineeDAOImpl.getById(traineeId);

        //Search for userId assigned to trainee
        int userId = trainee.getUserId();

        //Remove user
        userDAOImpl.removeById(userId);
        logger.info("User assigned to trainee successfully deleted");

        //Remove trainee
        traineeDAOImpl.removeById(traineeId);
        logger.info("Trainee successfully deleted");
        return trainee;
    }

    @Override
    public Trainee getTraineeById(int traineeId) {
        //Return specified trainee
        logger.info("Trainee redeemed");
        return traineeDAOImpl.getById(traineeId);
    }
}
