package org.spring.facade;

import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.Training;

import java.util.Date;

public interface Facade {
    // Methods related to Trainees
    int createTrainee(String name, String lastname, Date dateOfBirth, String address);
    Trainee updateTrainee(int traineeId, Date dateOfBirth, String address);
    Trainee deleteTrainee(int traineeId);
    Trainee getTraineeById(int traineeId);

    // Methods related to Trainers
    int createTrainer(String name, String lastname, int specialization);
    Trainer updateTrainer(int trainerId, int specialization);
    Trainer getTrainerById(int trainerId);

    // Methods related to Trainings
    int createTraining(String trainingName, Date trainingDate, int traineeId,
                       int trainingTypeId, int trainingDuration, int trainerId);
    Training getTrainingById(int trainingId);
}
