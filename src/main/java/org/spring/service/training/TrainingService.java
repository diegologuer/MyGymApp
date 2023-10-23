package org.spring.service.training;

import org.spring.model.Training;
import org.spring.repository.Trainee.TraineeDAO;
import org.spring.repository.Trainer.TrainerDAO;
import org.spring.repository.Training.TrainingDAO;
import org.spring.repository.TrainingType.TrainingTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class TrainingService implements TrainingServ {

    private TrainingTypeDAO trainingTypeDAO;
    private TrainingDAO trainingDAO;
    private TraineeDAO traineeDAO;
    private TrainerDAO trainerDAO;

    @Autowired
    public TrainingService(TrainingTypeDAO trainingTypeDAO, TrainingDAO trainingDAO, TraineeDAO traineeDAO, TrainerDAO trainerDAO){
        this.trainingTypeDAO = trainingTypeDAO;
        this.trainingDAO = trainingDAO;
        this.traineeDAO = traineeDAO;
        this.trainerDAO = trainerDAO;
    }

    @Override
    public int createTraining(String trainingName, Date trainingDate, int traineeId,
                               int trainingTypeId, int trainingDuration, int trainerId) {
        if(traineeDAO.getById(traineeId)==null){
            throw new IllegalArgumentException("Trainee with id: " + traineeId + " is not registered");
        }
        if(trainingTypeDAO.getById(trainingTypeId)==null){
            throw new IllegalArgumentException("Training type with id: " + trainingTypeId + " is not registered");
        }
        if(trainerDAO.getById(trainerId)==null){
            throw new IllegalArgumentException("Training type with id: " + trainerId + " is not registered");
        }
        int id = trainingDAO.nextAvailableId();
        return trainingDAO.save(new Training(id,trainingName,trainingDate,traineeId,trainingTypeId,trainingDuration,trainerId));

    }

    @Override
    public Training getTrainingById(int trainingId) {

        Training training = trainingDAO.getById(trainingId);

        if(training==null){
            throw new IllegalArgumentException("Training with id: " + trainingId + " is not registered");
        }
            return training;


    }


}
