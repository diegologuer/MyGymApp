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

    private TrainerServiceImpl trainerService;

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
        trainerDAO = mock(TrainerDAOImpl.class);
        credentialsService = mock(CredentialsService.class);
        userDAO = mock(UserDAOImpl.class);
        trainingTypeDAO = mock(TrainingTypeDAO.class);
        trainerService = new TrainerServiceImpl(trainerDAO, credentialsService, userDAO, trainingTypeDAO);
    }

    @Test
    public void givenValidNameAndLastName_whenCreateTrainer_thenTrainerIsCreated() {
        String name = "John";
        String lastName = "Doe";
        int specialization = 1;
        when(credentialsService.generateUsername(name, lastName)).thenReturn("John.Doe");
        when(credentialsService.generatePassword()).thenReturn("randomPassword");
        when(trainingTypeDAO.getById(specialization)).thenReturn(new TrainingType(specialization, "Specialization"));
        when(trainerDAO.nextAvailableId()).thenReturn(1);
        when(userDAO.nextAvailableId()).thenReturn(1);
        when(trainerDAO.save(any(Trainer.class))).thenReturn(1);
        when(userDAO.save(any(User.class))).thenReturn(1);
        int trainerId = trainerService.createTrainer(name, lastName, specialization);
        assertEquals(1, trainerId);
    }

    @Test
    public void givenTrainerIdAndSpecialization_whenUpdateTrainer_thenTrainerIsUpdated() {
        int trainerId = 1;
        int specialization = 2;
        when(trainingTypeDAO.getById(specialization)).thenReturn(new TrainingType(specialization, "New Specialization"));
        Trainer trainer = new Trainer(trainerId, 1, 1);
        when(trainerDAO.getById(trainerId)).thenReturn(trainer);
        Trainer updatedTrainer = trainerService.updateTrainer(trainerId, specialization);
        assertEquals(specialization, updatedTrainer.getSpecialization());
    }

    @Test
    public void givenTrainerId_whenGetTrainerById_thenTrainerIsRetrieved() {
        int trainerId = 1;
        Trainer trainer = new Trainer(trainerId, 1, 1);
        when(trainerDAO.getById(trainerId)).thenReturn(trainer);
        Trainer retrievedTrainer = trainerService.getTrainerById(trainerId);
        assertEquals(trainerId, retrievedTrainer.getId());
    }
}

