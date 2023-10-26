package spring.facade;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spring.facade.Facade;
import org.spring.facade.FacadeImpl;
import org.spring.model.Trainee;
import org.spring.model.Trainer;
import org.spring.model.Training;
import org.spring.service.trainee.TraineeService;
import org.spring.service.trainer.TrainerService;
import org.spring.service.training.TrainingService;

import java.util.Date;

public class FacadeImplTest {
    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    private Facade facade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        facade = new FacadeImpl(traineeService, trainerService, trainingService);
    }


    // Trainee methods tests
    @Test

    public void givenValidArgs_whenCreateTrainee_thenIsCreated() {
        //Arrange
        int traineeId = 1;
        Mockito.when(traineeService.createTrainee(Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.anyString()))
                .thenReturn(traineeId);

        //Act
        int createdTraineeId = facade.createTrainee("John", "Doe", new Date(), "Address");

        //Assert
        assertEquals(traineeId, createdTraineeId);
    }

    @Test
    public void givenValidArgs_whenUpdateTrainee_thenIsUpdated() {
        // Arrange
        Trainee updatedTrainee = new Trainee(1, new Date(), "New Address", 1);
        Mockito.when(traineeService.updateTrainee(1, new Date(), "New Address")).thenReturn(updatedTrainee);

        // Act
        Trainee result = facade.updateTrainee(1, new Date(), "New Address");

        // Assert
        assertEquals(updatedTrainee, result);
    }

    @Test
    public void givenValidArgs_whenDeleteTrainee_thenIsDeleted() {
        // Arrange
        Trainee deletedTrainee = new Trainee(1, new Date(), "123 Gym St, Fitness City", 1);
        Mockito.when(traineeService.deleteTrainee(1)).thenReturn(deletedTrainee);

        // Act
        Trainee result = facade.deleteTrainee(1);

        // Assert
        assertEquals(deletedTrainee, result);
    }

    @Test
    public void givenValidArgs_whenGetTraineeById_thenShouldReturnTrainee() {
        // Arrange
        Trainee trainee = new Trainee(1, new Date(), "123 Gym St, Fitness City", 1);
        Mockito.when(traineeService.getTraineeById(1)).thenReturn(trainee);

        // Act
        Trainee result = facade.getTraineeById(1);

        // Assert
        assertEquals(trainee, result);
    }

    // Trainer methods tests
    @Test
    public void givenValidArgs_whenCreateTrainer_thenShouldReturnTrainerId() {
        //Arrange
        Mockito.when(trainerService.createTrainer("John", "Doe", 2)).thenReturn(1);

        //Act
        int result = facade.createTrainer("John", "Doe", 2);

        //Assert
        assertEquals(1, result);
    }

    @Test
    public void givenValidArgs_whenUpdateTrainer_thenShouldReturnUpdatedTrainer() {
        Trainer updatedTrainer = new Trainer(1, 4, 2);
        Mockito.when(trainerService.updateTrainer(1, 2)).thenReturn(updatedTrainer);
        Trainer result = facade.updateTrainer(1, 2);
        assertEquals(updatedTrainer, result);
    }

    @Test
    public void givenValidArgs_whenGetTrainerById_thenShouldReturnTrainer() {
        Trainer trainer = new Trainer(1, 4, 2);
        Mockito.when(trainerService.getTrainerById(1)).thenReturn(trainer);
        Trainer result = facade.getTrainerById(1);
        assertEquals(trainer, result);
    }

    //Trainer test methods
    @Test
    public void givenValidArgs_whenCreateTraining_thenShouldReturnTrainingId() {
        String trainingName = "Training Name";
        Date trainingDate = new Date();
        int traineeId = 1;
        int trainingTypeId = 2;
        int trainingDuration = 3;
        int trainerId = 4;
        int trainingIdGenerated = 123;
        Mockito.when(trainingService.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId))
                .thenReturn(trainingIdGenerated);
        int result = facade.createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
        Mockito.verify(trainingService).createTraining(trainingName, trainingDate, traineeId, trainingTypeId, trainingDuration, trainerId);
        assertEquals(trainingIdGenerated, result);
    }

    @Test
    public void givenValidArgs_whenGetTrainingById_thenShouldReturnTraining() {
        Training training = new Training(1, "Cardio Kickboxing Class", new Date(), 1, 2, 60, 1);
        Mockito.when(trainingService.getTrainingById(1)).thenReturn(training);
        Training result = facade.getTrainingById(1);
        assertEquals(training, result);
    }
}
