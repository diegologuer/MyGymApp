package org.spring.service.trainee;

import org.spring.model.Trainee;
import org.spring.repository.trainee.TraineeDAO;
import org.spring.repository.trainee.TraineeDAOImpl;
import org.spring.repository.user.UserDAO;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeDAO traineeDAO;
    private final CredentialsService credentialsService;
    private final UserDAO userDAO;
    private static final Logger logger = Logger.getLogger(TraineeServiceImpl.class.getName());

    @Autowired
    public TraineeServiceImpl(TraineeDAO traineeDAO, CredentialsService credentialsService, UserDAO userDAO) {
        this.traineeDAO = traineeDAO;
        this.credentialsService = credentialsService;
        this.userDAO = userDAO;
    }

    @Override
    public int createTrainee(String name, String lastname, Date dateOfBirth, String address) {

        // Validate dateOfBirth and address
        validateDateOfBirth(dateOfBirth);
        validateAddress(address);

        //Generating user, name and lastname are validated in credentialService
        int userId = credentialsService.createPersonProfile(name, lastname);

        //Assigning available IDs
        int traineeId = traineeDAO.nextAvailableId();

        //Return the ID number of the created Trainee
        return traineeDAO.save(new Trainee(traineeId, dateOfBirth, address, userId));
    }

    @Override
    public Trainee updateTrainee(int traineeId, Date dateOfBirth, String address) {
        // Validate dateOfBirth and address
        validateDateOfBirth(dateOfBirth);
        validateAddress(address);

        //Check trainee existence
        Trainee trainee = traineeDAO.getById(traineeId);

        logger.info("Setting new data...");
        //Set data
        trainee.setAddress(address);
        trainee.setDateOfBirth(dateOfBirth);

        //Save trainee to replace previous
        traineeDAO.save(trainee);
        logger.info("Trainee updated");
        return trainee;
    }

    @Override
    public Trainee deleteTrainee(int traineeId) {
        //Search for the trainee, this also validates if exists
        Trainee trainee = traineeDAO.getById(traineeId);

        //Search for userId assigned to trainee
        int userId = trainee.getUserId();

        //Remove user
        userDAO.removeById(userId);
        logger.info("User assigned to trainee successfully deleted");

        //Remove trainee
        traineeDAO.removeById(traineeId);
        logger.info("Trainee successfully deleted");
        return trainee;
    }

    @Override
    public Trainee getTraineeById(int traineeId) {
        //Return specified trainee
        logger.info("Trainee redeemed");
        //A validation occurs in traineeDao as well
        return traineeDAO.getById(traineeId);
    }

    private void validateDateOfBirth(Date dateOfBirth) {
        // Perform your dateOfBirth validation logic
        if (dateOfBirth == null || dateOfBirth.after(new Date())) {
            throw new IllegalArgumentException("Invalid date of birth");
        }
    }

    private void validateAddress(String address) {
        // Perform your address validation logic
        if (address == null || address.trim().isEmpty() || address.length() < 5) {
            throw new IllegalArgumentException("Invalid address");
        }
    }
}
