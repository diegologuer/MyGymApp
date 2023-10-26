package spring.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.spring.model.Trainer;
import org.spring.model.TrainingType;
import org.spring.model.User;
import org.spring.repository.trainer.TrainerDAOImpl;
import org.spring.repository.trainingType.TrainingTypeDAO;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.spring.service.trainer.TrainerServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TrainerServiceImplTest {

    private TrainerServiceImpl systemUnderTest;

    @Mock
    private TrainerDAOImpl trainerDAO;
    @Mock
    private CredentialsService credentialsService;
    @Mock
    private UserDAOImpl userDAO;
    @Mock
    private TrainingTypeDAO trainingTypeDAO;

    @Before
    public void setUp() {
        // Arrange
        trainerDAO = mock(TrainerDAOImpl.class);
        credentialsService = mock(CredentialsService.class);
        userDAO = mock(UserDAOImpl.class);
        trainingTypeDAO = mock(TrainingTypeDAO.class);
        systemUnderTest = new TrainerServiceImpl(trainerDAO, credentialsService, userDAO, trainingTypeDAO);
    }

    @Test
    public void givenValidNameAndLastName_whenCreateTrainer_thenTrainerIsCreated() {
        // Arrange
        String name = "John";
        String lastName = "Doe";
        int expectedTrainerId = 1;
        when(credentialsService.generateUsername(name, lastName)).thenReturn("John.Doe");
        when(credentialsService.generatePassword()).thenReturn("randomPassword");
        when(trainingTypeDAO.getById(expectedTrainerId)).thenReturn(new TrainingType(expectedTrainerId, "Specialization"));
        when(trainerDAO.nextAvailableId()).thenReturn(1);
        when(userDAO.nextAvailableId()).thenReturn(1);
        when(trainerDAO.save(any(Trainer.class))).thenReturn(1);
        when(userDAO.save(any(User.class))).thenReturn(1);

        // Act
        int actualTrainerId = systemUnderTest.createTrainer(name, lastName, expectedTrainerId);

        // Assert
        assertEquals(expectedTrainerId, actualTrainerId);
    }

    @Test
    public void givenTrainerIdAndSpecialization_whenUpdateTrainer_thenTrainerIsUpdated() {
        // Arrange
        int trainerId = 1;
        int expectedTrainerTypeId = 2;
        when(trainingTypeDAO.getById(expectedTrainerTypeId)).thenReturn(new TrainingType(expectedTrainerTypeId, "New Specialization"));
        Trainer trainer = new Trainer(trainerId, 1, 1);
        when(trainerDAO.getById(trainerId)).thenReturn(trainer);

        // Act
        Trainer actualTrainer = systemUnderTest.updateTrainer(trainerId, expectedTrainerTypeId);

        // Assert
        assertEquals(expectedTrainerTypeId, actualTrainer.getSpecialization());
    }

    @Test
    public void givenTrainerId_whenGetTrainerById_thenTrainerIsRetrieved() {
        // Arrange
        int expectedTrainerId = 1;
        Trainer trainer = new Trainer(expectedTrainerId, 1, 1);
        when(trainerDAO.getById(expectedTrainerId)).thenReturn(trainer);

        // Act
        Trainer actualTrainer = systemUnderTest.getTrainerById(expectedTrainerId);

        // Assert
        assertEquals(expectedTrainerId, actualTrainer.getId());
    }
}

