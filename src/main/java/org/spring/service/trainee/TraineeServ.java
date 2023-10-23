package org.spring.service.trainee;

import org.spring.model.Trainee;
import java.util.Date;


public interface TraineeServ {

    int createTrainee(String name, String lastname, Date dateOfBirth, String address);

    Trainee updateTrainee(int traineeId, Date dateOfBirth, String address);

    Trainee deleteTrainee(int traineeId);

    Trainee getTraineeById(int traineeId);

}