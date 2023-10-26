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

    private TrainingServiceImpl systemUnderTest;

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
        // Arrange
        trainingTypeDAO = mock(TrainingTypeDAO.class);
        trainingDAO = mock(TrainingDAO.class);
        traineeDAO = mock(TraineeDAO.class);
        trainerDAO = mock(TrainerDAO.class);
        systemUnderTest = new TrainingServiceImpl(trainingTypeDAO, trainingDAO, traineeDAO, trainerDAO);
    }

    @Test
    public void givenValidTrainingData_whenCreateTraining_thenTrainingIsCreated() {
        // Arrange
        String trainingName = "Java Programming";
        Date trainingDate = new Date(System.currentTimeMillis() + 3600000); // Set the date 1 hour in the future
        int expectedTraineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 60;
        int trainerId = 3;
        when(trainingTypeDAO.getById(trainingTypeId)).thenReturn(new TrainingType(trainingTypeId, "Programming"));
        when(traineeDAO.getById(expectedTraineeId)).thenReturn(new Trainee(expectedTraineeId, new Date(), "Address", 1));
        when(trainerDAO.getById(trainerId)).thenReturn(new Trainer(trainerId, 1, 2));
        when(trainingDAO.nextAvailableId()).thenReturn(1);
        when(trainingDAO.save(any(Training.class))).thenReturn(1);

        // Act
        int actualTrainingId = systemUnderTest.createTraining(trainingName, trainingDate, expectedTraineeId, trainingTypeId, trainingDuration, trainerId);

        // Assert
        assertEquals(expectedTraineeId, actualTrainingId);
    }

    @Test
    public void givenInvalidTrainingData_whenCreateTraining_thenShouldThrow() {
        // Arrange
        String trainingName = "X";
        Date trainingDate = new Date(System.currentTimeMillis() - 3600000); // Set the date 1 hour in the past
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 5; // Less than 10
        int trainerId = 3;

        when(trainingTypeDAO.getById(trainingTypeId)).thenReturn(new TrainingType(trainingTypeId, "Programming"));
        when(traineeDAO.getById(traineeId)).thenReturn(new Trainee(traineeId, new Date(), "Address", 1));
        when(trainerDAO.getById(trainerId)).thenReturn(new Trainer(trainerId, 1, 2));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> systemUnderTest.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId));
    }

    @Test
    public void givenTrainingId_whenGetTrainingById_thenTrainingIsRetrieved() {
        // Arrange
        int expectedTrainingId = 1;
        Training training = new Training(expectedTrainingId, "Java Programming", new Date(), 1, 2, 60, 3);
        when(trainingDAO.getById(expectedTrainingId)).thenReturn(training);

        // Act
        Training actualTraining = systemUnderTest.getTrainingById(expectedTrainingId);

        // Assert
        assertEquals(expectedTrainingId, actualTraining.getId());
    }

}
