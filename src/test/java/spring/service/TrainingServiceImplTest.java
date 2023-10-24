package spring.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.Training;
import org.spring.model.TrainingType;
import org.spring.repository.trainee.TraineeDAO;
import org.spring.repository.trainer.TrainerDAO;
import org.spring.repository.training.TrainingDAO;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.spring.service.training.TrainingServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Date;

public class TrainingServiceImplTest {

    private TrainingServiceImpl trainingService;

    @Mock
    private TrainingTypeDAO trainingTypeDAO;
    @Mock
    private TrainingDAO trainingDAO;
    @Mock
    private TraineeDAO traineeDAO;
    @Mock
    private TrainerDAO trainerDAO;

    @Before
    public void setUp() {
        trainingTypeDAO = mock(TrainingTypeDAO.class);
        trainingDAO = mock(TrainingDAO.class);
        traineeDAO = mock(TraineeDAO.class);
        trainerDAO = mock(TrainerDAO.class);
        trainingService = new TrainingServiceImpl(trainingTypeDAO, trainingDAO, traineeDAO, trainerDAO);
    }

    @Test
    public void testCreateTraining() {
        // Prepare data
        String trainingName = "Java Programming";
        Date trainingDate = new Date(System.currentTimeMillis() + 3600000); // Set the date 1 hour in the future
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 60;
        int trainerId = 3;

        // Define the behavior of the trainingTypeDAO.getById, traineeDAO.getById, and trainerDAO.getById methods
        when(trainingTypeDAO.getById(trainingTypeId)).thenReturn(new TrainingType(trainingTypeId, "Programming"));
        when(traineeDAO.getById(traineeId)).thenReturn(new Trainee(traineeId, new Date(), "Address", 1));
        when(trainerDAO.getById(trainerId)).thenReturn(new Trainer(trainerId, 1, 2));

        // Define the behavior of the trainingDAO.nextAvailableId and trainingDAO.save methods
        when(trainingDAO.nextAvailableId()).thenReturn(1);
        when(trainingDAO.save(any(Training.class))).thenReturn(1);

        // Create the training
        int trainingId = trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);

        // Verify that the training was created and returned with the correct ID
        assertEquals(1, trainingId);
    }

    @Test
    public void testCreateTrainingWithInvalidData() {
        // Prepare data with invalid training name, past training date, and insufficient duration
        String trainingName = "X";
        Date trainingDate = new Date(System.currentTimeMillis() - 3600000); // Set the date 1 hour in the past
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 5; // Less than 10
        int trainerId = 3;

        // Define the behavior of the trainingTypeDAO.getById, traineeDAO.getById, and trainerDAO.getById methods
        when(trainingTypeDAO.getById(trainingTypeId)).thenReturn(new TrainingType(trainingTypeId, "Programming"));
        when(traineeDAO.getById(traineeId)).thenReturn(new Trainee(traineeId, new Date(), "Address", 1));
        when(trainerDAO.getById(trainerId)).thenReturn(new Trainer(trainerId, 1, 2));

        // Attempt to create the training with invalid data and expect an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
        });
    }

    @Test
    public void testGetTrainingById() {
        // Prepare data
        int trainingId = 1;

        // Mock the behavior of the trainingDAO.getById method
        Training training = new Training(trainingId, "Java Programming", new Date(), 1, 2, 60, 3);
        when(trainingDAO.getById(trainingId)).thenReturn(training);

        // Get the training by ID
        Training retrievedTraining = trainingService.getTrainingById(trainingId);

        // Verify that the correct training was retrieved
        assertEquals(trainingId, retrievedTraining.getid());
    }
}
