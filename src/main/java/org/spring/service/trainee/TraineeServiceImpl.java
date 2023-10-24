package org.spring.service.trainee;

import org.spring.model.Trainee;
import org.spring.model.User;
import org.spring.repository.trainee.TraineeDAOImpl;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TraineeServiceImpl implements TraineeService {

    private TraineeDAOImpl traineeDAOImpl;
    private CredentialsService credentialsService;
    private UserDAOImpl userDAOImpl;

    @Autowired
    public TraineeServiceImpl(TraineeDAOImpl traineeDAOImpl, CredentialsService credentialsService, UserDAOImpl userDAOImpl) {
        this.traineeDAOImpl = traineeDAOImpl;
        this.credentialsService = credentialsService;
        this.userDAOImpl = userDAOImpl;
    }

    @Override
    public int createTrainee(String name, String lastname, Date dateOfBirth, String address) {

        //Validate name and lastname
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid name");
        }
        if (lastname == null || lastname.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid username");
        }

        //Assigning username and password
        String username = credentialsService.generateUsername(name, lastname);
        String password = credentialsService.generatePassword();

        //Assigning available IDs
        int traineeId = traineeDAOImpl.nextAvailableId();
        int userId = userDAOImpl.nextAvailableId();

        //Saving trainee
        userDAOImpl.save(new User(userId, name, lastname, username, password, true));

        //Return the ID number of the created Trainee
        return traineeDAOImpl.save(new Trainee(traineeId, dateOfBirth, address, userId));
    }

    @Override
    public Trainee updateTrainee(int traineeId, Date dateOfBirth, String address) {
        //Check trainee existence
        Trainee trainee = traineeDAOImpl.getById(traineeId);

        //Set data
        trainee.setAddress(address);
        trainee.setDateOfBirth(dateOfBirth);

        //Save trainee to replace previous
        traineeDAOImpl.save(trainee);
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
        System.out.println("User assigned to trainee successfully deleted");

        //Remove trainee
        traineeDAOImpl.removeById(traineeId);
        System.out.println("Trainee successfully deleted");
        return trainee;
    }

    @Override
    public Trainee getTraineeById(int traineeId) {
        //Return specified trainee
        Trainee trainee = traineeDAOImpl.getById(traineeId);
        return trainee;
    }


}
