package org.spring.service.trainer;


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

        if(trainingTypeDAO.getById(specialization)==null){
            throw new IllegalArgumentException("Specialization with id: " + specialization + " is not registered");
        }

        String username = credentialsGenerator.generateUsername(name, lastname);
        String password = credentialsGenerator.generatePassword();
        int trainerId = trainerDAOImpl.nextAvailableId();
        int userId = userDAOImpl.nextAvailableId();
        userDAOImpl.save(new User(userId, name, lastname, username, password, true));
        return trainerDAOImpl.save(new Trainer(trainerId, userId, specialization));

    }

    @Override
    public Trainer updateTrainer(int trainerId, int specialization) {
        Trainer trainer = trainerDAOImpl.getById(trainerId);
        if(trainer == null){
            throw new IllegalArgumentException("Trainer id: " + trainerId + " is not registered");
        }
        trainer.setSpecialization(specialization);
        trainerDAOImpl.save(trainer);
        return trainer;
    }

    @Override
    public Trainer getTrainerById(int trainerId) {

        Trainer trainer = trainerDAOImpl.getById(trainerId);
        if(trainer == null){
            throw new IllegalArgumentException("Trainer with id: " + trainerId + " is not registered");
        }
        return trainer;
    }
}

