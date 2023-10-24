package org.spring.service.trainer;


import org.spring.model.Trainer;
import org.spring.model.User;
import org.spring.repository.trainer.TrainerDAOImpl;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {


    private final TrainerDAOImpl trainerDAOImpl;
    private final CredentialsService credentialsService;
    private final UserDAOImpl userDAOImpl;
    private final TrainingTypeDAO trainingTypeDAO;

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

        //Validate name and lastname
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid name");
        }
        if (lastname == null || lastname.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid username");
        }
        //Check specialization id existence
        trainingTypeDAO.getById(specialization);

        //Assigning username and password
        String username = credentialsService.generateUsername(name, lastname);
        String password = credentialsService.generatePassword();

        //Assigning available IDs
        int trainerId = trainerDAOImpl.nextAvailableId();
        int userId = userDAOImpl.nextAvailableId();

        //Saving trainee
        userDAOImpl.save(new User(userId, name, lastname, username, password, true));

        //Return the ID number of the created Trainee
        return trainerDAOImpl.save(new Trainer(trainerId, userId, specialization));

    }

    @Override
    public Trainer updateTrainer(int trainerId, int specialization) {
        //Check if specialization id exists
        trainingTypeDAO.getById(specialization);

        //Search fot trainer
        Trainer trainer = trainerDAOImpl.getById(trainerId);

        //Set new specialization
        trainer.setSpecialization(specialization);

        //Save trainer
        trainerDAOImpl.save(trainer);
        return trainer;
    }

    @Override
    public Trainer getTrainerById(int trainerId) {

        return trainerDAOImpl.getById(trainerId);
    }
}

