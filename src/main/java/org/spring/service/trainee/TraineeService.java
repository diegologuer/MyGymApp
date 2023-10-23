package org.spring.service.trainee;

import org.spring.model.Trainee;
import org.spring.model.User;
import org.spring.repository.Trainee.TraineeDAOImpl;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class TraineeService implements TraineeServ{

    private TraineeDAOImpl traineeDAOImpl;
    private CredentialsGenerator credentialsGenerator;
    private UserDAOImpl userDAOImpl;

    @Autowired
    public TraineeService(TraineeDAOImpl traineeDAOImpl, CredentialsGenerator credentialsGenerator, UserDAOImpl userDAOImpl) {
        this.traineeDAOImpl = traineeDAOImpl;
        this.credentialsGenerator = credentialsGenerator;
        this.userDAOImpl = userDAOImpl;
    }

    @Override
    public int createTrainee(String name, String lastname, Date dateOfBirth, String address) {

        String username = credentialsGenerator.generateUsername(name, lastname);
        String password = credentialsGenerator.generatePassword();
        int traineeId = traineeDAOImpl.nextAvailableId();
        int userId = userDAOImpl.nextAvailableId();
        userDAOImpl.save(new User(userId, name, lastname,username,password, true));
        return traineeDAOImpl.save(new Trainee(traineeId, dateOfBirth, address, userId));


    }

    @Override
    public Trainee updateTrainee(int traineeId, Date dateOfBirth, String address) {
        Trainee trainee = traineeDAOImpl.getById(traineeId);
        if(trainee == null){
            throw new IllegalArgumentException("Trainee with id: " + traineeId + " is not registered.");
        }
        trainee.setAddress(address);
        trainee.setDateOfBirth(dateOfBirth);
        traineeDAOImpl.save(trainee);
        return trainee;
    }

    @Override
    public Trainee deleteTrainee(int traineeId) {
    Trainee trainee = traineeDAOImpl.removeById(traineeId);
    if(trainee == null){
        throw new IllegalArgumentException("Trainee with id: " + traineeId + " is not registered");
    }
    return trainee;
    }

    @Override
    public Trainee getTraineeById(int traineeId) {
        Trainee trainee  = traineeDAOImpl.getById(traineeId);
        if(trainee == null){
            throw new IllegalArgumentException("Trainee with id: " + traineeId + " is not registered");
        }
        return trainee;
    }


}
