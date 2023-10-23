package org.spring.facade;

import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.Training;
import org.spring.service.trainee.TraineeService;
import org.spring.service.trainer.TrainerService;
import org.spring.service.training.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FacadeImpl implements Facade{
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public FacadeImpl(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    //Trainee methods
    public int createTrainee(String name, String lastname, Date dateOfBirth, String address) {
        return traineeService.createTrainee(name, lastname, dateOfBirth, address);
    }

    public Trainee updateTrainee(int traineeId, Date dateOfBirth, String address) {
        return traineeService.updateTrainee(traineeId, dateOfBirth, address);
    }

    public Trainee deleteTrainee(int traineeId) {
        return traineeService.deleteTrainee(traineeId);
    }

    public Trainee getTraineeById(int traineeId) {
        return traineeService.getTraineeById(traineeId);
    }

    //Trainer methods
    public int createTrainer(String name, String lastname, int specialization) {
        return trainerService.createTrainer(name, lastname, specialization);
    }

    public Trainer updateTrainer(int trainerId, int specialization) {
        return trainerService.updateTrainer(trainerId, specialization);
    }

    public Trainer getTrainerById(int trainerId) {
        return trainerService.getTrainerById(trainerId);
    }

    //Training methods
    public int createTraining(String trainingName, Date trainingDate, int traineeId,
                              int trainingTypeId, int trainingDuration, int trainerId) {
        return trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
    }

    public Training getTrainingById(int trainingId) {
        return trainingService.getTrainingById(trainingId);
    }
}
