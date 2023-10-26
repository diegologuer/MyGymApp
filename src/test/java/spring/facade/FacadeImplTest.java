package spring.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.spring.facade.FacadeImpl;
import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.Training;
import org.spring.service.trainee.TraineeService;
import org.spring.service.trainer.TrainerService;
import org.spring.service.training.TrainingService;

import java.util.Date;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class FacadeImplTest {

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private FacadeImpl systemUnderTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        systemUnderTest = new FacadeImpl(traineeService, trainerService, trainingService);
    }

    @Test
    public void givenValidArgs_whenCreateTrainee_thenIsCreated() {
        // Arrange
        int expectedTraineeId = 1;
        String name = "John";
        String lastname = "Doe";
        Date dateOfBirth = new Date();
        String address = "123 Main St";

        when(traineeService.createTrainee(name, lastname, dateOfBirth, address)).thenReturn(1);

        // Act
        int actualTraineeId = systemUnderTest.createTrainee(name, lastname, dateOfBirth, address);

        // Assert
        assertEquals(expectedTraineeId, actualTraineeId);
        verify(traineeService).createTrainee(name, lastname, dateOfBirth, address);
    }

    @Test
    public void givenValidArgs_whenUpdateTrainee_thenIsUpdated() {
        // Arrange
        int traineeId = 1;
        Date dateOfBirth = new Date();
        String address = "456 Elm St";

        when(traineeService.updateTrainee(traineeId, dateOfBirth, address)).thenReturn(new Trainee());

        // Act
        Trainee result = systemUnderTest.updateTrainee(traineeId, dateOfBirth, address);

        // Assert
        assertNotNull(result);
        verify(traineeService).updateTrainee(traineeId, dateOfBirth, address);
    }

    @Test
    public void givenValidArgs_whenDeleteTrainee_thenIsDeleted() {
        // Arrange
        int traineeId = 1;
        Trainee expectedDeletedTrainee = new Trainee();
        when(traineeService.deleteTrainee(traineeId)).thenReturn(expectedDeletedTrainee);

        // Act
        Trainee actualDeletedTrainee = systemUnderTest.deleteTrainee(traineeId);

        // Assert
        assertNotNull(actualDeletedTrainee);
        assertEquals(expectedDeletedTrainee, actualDeletedTrainee);
        verify(traineeService).deleteTrainee(traineeId);
    }

    @Test
    public void givenValidArgs_whenGetTraineeById_thenShouldReturnTrainee() {
        // Arrange
        int expectedTraineeId = 1;
        Trainee expectedTrainee = new Trainee();
        when(traineeService.getTraineeById(expectedTraineeId)).thenReturn(expectedTrainee);

        // Act
        Trainee actualTrainee = systemUnderTest.getTraineeById(expectedTraineeId);

        // Assert
        assertNotNull(actualTrainee);
        assertEquals(expectedTrainee, actualTrainee);
        verify(traineeService).getTraineeById(expectedTraineeId);
    }

    // Trainer methods tests
    @Test
    public void givenValidArgs_whenCreateTrainer_thenShouldReturnTrainerId() {
        //Arrange
        int expectedTraineeId = 1;
        when(trainerService.createTrainer("John", "Doe", 2)).thenReturn(1);

        //Act
        int actualTraineeId = systemUnderTest.createTrainer("John", "Doe", 2);

        //Assert
        assertEquals(expectedTraineeId, actualTraineeId);
    }

    @Test
    public void givenValidArgs_whenUpdateTrainer_thenShouldReturnUpdatedTrainer() {
        // Arrange
        Trainer expectedUpdatedTrainer = new Trainer(1, 4, 2);
        when(trainerService.updateTrainer(1, 2)).thenReturn(expectedUpdatedTrainer);

        // Act
        Trainer actualUpdatedTrainer = systemUnderTest.updateTrainer(1, 2);

        // Assert
        assertEquals(expectedUpdatedTrainer, actualUpdatedTrainer);
    }

    @Test
    public void givenValidArgs_whenGetTrainerById_thenShouldReturnTrainer() {
        // Arrange
        int trainerId = 1;
        Trainer expectedTrainer = new Trainer();
        when(trainerService.getTrainerById(trainerId)).thenReturn(expectedTrainer);

        // Act
        Trainer actualTrainer = systemUnderTest.getTrainerById(trainerId);

        // Assert
        assertNotNull(actualTrainer);
        assertEquals(expectedTrainer, actualTrainer);
        verify(trainerService).getTrainerById(trainerId);
    }

    @Test
    public void givenValidArgs_whenCreateTraining_thenShouldReturnTrainingId() {
        // Arrange
        String trainingName = "Training ABC";
        Date trainingDate = new Date();
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 3;
        int trainerId = 4;

        when(trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId))
                .thenReturn(1);

        // Act
        int result = systemUnderTest.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);

        // Assert
        assertEquals(1, result);
        verify(trainingService).createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
    }

    @Test
    public void givenValidArgs_whenGetTrainingById_thenShouldReturnTraining() {
        // Arrange
        Training expectedTraining = new Training(1, "Cardio Kickboxing Class", new Date(), 1, 2, 60, 1);
        when(trainingService.getTrainingById(1)).thenReturn(expectedTraining);

        // Act
        Training actualTraining = systemUnderTest.getTrainingById(1);

        // Assert
        assertEquals(expectedTraining, actualTraining);
    }
}


