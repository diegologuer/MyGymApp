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
        String trainingName = "Java Programming";
        Date trainingDate = new Date(System.currentTimeMillis() + 3600000); // Set the date 1 hour in the future
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 60;
        int trainerId = 3;
        when(trainingTypeDAO.getById(trainingTypeId)).thenReturn(new TrainingType(trainingTypeId, "Programming"));
        when(traineeDAO.getById(traineeId)).thenReturn(new Trainee(traineeId, new Date(), "Address", 1));
        when(trainerDAO.getById(trainerId)).thenReturn(new Trainer(trainerId, 1, 2));
        when(trainingDAO.nextAvailableId()).thenReturn(1);
        when(trainingDAO.save(any(Training.class))).thenReturn(1);
        int trainingId = trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
        assertEquals(1, trainingId);
    }

    @Test
    public void testCreateTrainingWithInvalidData() {
        String trainingName = "X";
        Date trainingDate = new Date(System.currentTimeMillis() - 3600000); // Set the date 1 hour in the past
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 5; // Less than 10
        int trainerId = 3;

        when(trainingTypeDAO.getById(trainingTypeId)).thenReturn(new TrainingType(trainingTypeId, "Programming"));
        when(traineeDAO.getById(traineeId)).thenReturn(new Trainee(traineeId, new Date(), "Address", 1));
        when(trainerDAO.getById(trainerId)).thenReturn(new Trainer(trainerId, 1, 2));
        assertThrows(IllegalArgumentException.class, () -> {
            trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
        });
    }

    @Test
    public void testGetTrainingById() {
        int trainingId = 1;
        Training training = new Training(trainingId, "Java Programming", new Date(), 1, 2, 60, 3);
        when(trainingDAO.getById(trainingId)).thenReturn(training);
        Training retrievedTraining = trainingService.getTrainingById(trainingId);
        assertEquals(trainingId, retrievedTraining.getid());
    }
}
