package org.spring.service.training;

import org.spring.model.Training;
import org.spring.repository.trainee.TraineeDAO;
import org.spring.repository.trainer.TrainerDAO;
import org.spring.repository.training.TrainingDAO;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingTypeDAO trainingTypeDAO;
    private final TrainingDAO trainingDAO;
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;

    @Autowired
    public TrainingServiceImpl(TrainingTypeDAO trainingTypeDAO, TrainingDAO trainingDAO, TraineeDAO traineeDAO, TrainerDAO trainerDAO) {
        this.trainingTypeDAO = trainingTypeDAO;
        this.trainingDAO = trainingDAO;
        this.traineeDAO = traineeDAO;
        this.trainerDAO = trainerDAO;
    }

    @Override
    public int createTraining(String trainingName, Date trainingDate, int traineeId,
                              int trainingTypeId, int trainingDuration, int trainerId) {

        //Validate trainingName, trainingDate and trainingDuration
        if (trainingName == null || trainingName.length() < 3) {
            throw new IllegalArgumentException("Please enter a valid training name");
        }
        if (trainingDate == null || trainingDate.before(new Date())) {
            throw new IllegalArgumentException("Training date must be in the future.");
        }
        if (trainingDuration < 10) {
            throw new IllegalArgumentException("Training duration must be at least 10 minutes ");
        }

        //Checking existence of traineeId, trainingTypeId and trainerId
        traineeDAO.getById(traineeId);
        trainingTypeDAO.getById(trainingTypeId);
        trainerDAO.getById(trainerId);

        //Assigning available ID
        int id = trainingDAO.nextAvailableId();
        //Return the ID number of the created Training
        return trainingDAO.save(new Training(id, trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId));

    }

    @Override
    public Training getTrainingById(int trainingId) {

        //Searching for specified trainee
        return trainingDAO.getById(trainingId);
    }


}
