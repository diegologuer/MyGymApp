package org.spring.service.trainer;


import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.User;
import org.spring.repository.Trainer.TrainerDAOImpl;
import org.spring.repository.TrainingType.TrainingTypeDAO;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService implements TrainerServ{


    private TrainerDAOImpl trainerDAOImpl;
    private CredentialsGenerator credentialsGenerator;
    private UserDAOImpl userDAOImpl;
    private TrainingTypeDAO trainingTypeDAO;

    @Autowired
    public TrainerService(TrainerDAOImpl trainerDAOImpl, CredentialsGenerator credentialsGenerator,
                          UserDAOImpl userDAOImpl, TrainingTypeDAO trainingTypeDAO) {
        this.trainerDAOImpl = trainerDAOImpl;
        this.credentialsGenerator = credentialsGenerator;
        this.userDAOImpl = userDAOImpl;
        this.trainingTypeDAO = trainingTypeDAO;
    }

    @Override
    public int createTrainer(String name, String lastname, int specialization) {

        //Validate name and lastname
        if(name == null || name.length()<3){
            throw new IllegalArgumentException("Please enter a valid name");
        }
        if(lastname == null || lastname.length()<3){
            throw new IllegalArgumentException("Please enter a valid username");
        }
        //Check specialization id existence
        trainingTypeDAO.getById(specialization);

        //Assigning username and password
        String username = credentialsGenerator.generateUsername(name, lastname);
        String password = credentialsGenerator.generatePassword();

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

        Trainer trainer  = trainerDAOImpl.getById(trainerId);
        return trainer;
    }
}

