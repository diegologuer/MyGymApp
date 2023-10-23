package org.spring.facade;

import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.Training;
import org.spring.service.trainee.TraineeServ;
import org.spring.service.trainer.TrainerServ;
import org.spring.service.training.TrainingServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Facade {
    private final TraineeServ traineeServ;
    private final TrainerServ trainerServ;
    private final TrainingServ trainingServ;

    @Autowired
    public Facade(TraineeServ traineeServ, TrainerServ trainerServ, TrainingServ trainingServ){
        this.traineeServ = traineeServ;
        this.trainerServ = trainerServ;
        this.trainingServ = trainingServ;
    }

    //Trainee methods
    public int createTrainee(String name, String lastname, Date dateOfBirth, String address) {
        return traineeServ.createTrainee(name, lastname, dateOfBirth, address);
    }
    public Trainee updateTrainee(int traineeId, Date dateOfBirth, String address) {
        return traineeServ.updateTrainee(traineeId, dateOfBirth, address);
    }
    public Trainee deleteTrainee(int traineeId) {
        return traineeServ.deleteTrainee(traineeId);
    }

    public Trainee getTraineeById(int traineeId) {
        return traineeServ.getTraineeById(traineeId);
    }

    //Trainer methods
    public int createTrainer(String name, String lastname, int specialization){
        return trainerServ.createTrainer(name, lastname, specialization);
    }

    public Trainer updateTrainer(int trainerId, int specialization){
        return trainerServ.updateTrainer(trainerId, specialization);
    }

    public Trainer getTrainerById(int trainerId){
        return trainerServ.getTrainerById(trainerId);
    }

    //Training methods
    public int createTraining( String trainingName, Date trainingDate, int traineeId,
                                int trainingTypeId, int trainingDuration, int trainerId){
       return trainingServ.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
    }

    public Training getTrainingById(int trainingId){
        return  trainingServ.getTrainingById(trainingId);
    }
}
