package spring.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.spring.model.Trainee;
import org.spring.model.User;
import org.spring.repository.trainee.TraineeDAOImpl;
import org.spring.repository.user.UserDAOImpl;
import org.spring.service.profile.CredentialsService;
import org.spring.service.trainee.TraineeServiceImpl;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Date;

public class TraineeServiceImplTest {

    private TraineeServiceImpl traineeService;

    @Mock
    private TraineeDAOImpl traineeDAO;
    @Mock
    private CredentialsService credentialsService;
    @Mock
    private UserDAOImpl userDAO;

    @Before
    public void setUp() {
        // Arrange
        traineeDAO = mock(TraineeDAOImpl.class);
        credentialsService = mock(CredentialsService.class);
        userDAO = mock(UserDAOImpl.class);
        traineeService = new TraineeServiceImpl(traineeDAO, credentialsService, userDAO);
    }

    @Test
    public void givenValidNameAndLastName_whenCreateTrainee_thenTraineeIsCreated() {
        // Arrange
        String name = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String address = "123 Main St";
        when(credentialsService.generateUsername(name, lastName)).thenReturn("John.Doe");
        when(credentialsService.generatePassword()).thenReturn("randomPassword");
        when(traineeDAO.nextAvailableId()).thenReturn(1);
        when(userDAO.nextAvailableId()).thenReturn(1);
        when(traineeDAO.save(any(Trainee.class))).thenReturn(1);
        when(userDAO.save(any(User.class))).thenReturn(1);

        // Act
        int traineeId = traineeService.createTrainee(name, lastName, dateOfBirth, address);

        // Assert
        assertEquals(1, traineeId);
    }

    @Test
    public void givenTraineeIdAndDetails_whenUpdateTrainee_thenTraineeIsUpdated() {
        // Arrange
        int traineeId = 1;
        Date dateOfBirth = new Date();
        String address = "456 Elm St";
        Trainee trainee = new Trainee(traineeId, new Date(), "123 Main St", 1);
        when(traineeDAO.getById(traineeId)).thenReturn(trainee);

        // Act
        Trainee updatedTrainee = traineeService.updateTrainee(traineeId, dateOfBirth, address);

        // Assert
        assertEquals(dateOfBirth, updatedTrainee.getDateOfBirth());
        assertEquals(address, updatedTrainee.getAddress());
    }

    @Test
    public void givenTraineeId_whenDeleteTrainee_thenTraineeIsDeleted() {
        // Arrange
        int traineeId = 1;
        int userId = 123;
        Trainee trainee = new Trainee(1, new Date(), "Address", 123);
        Mockito.when(traineeDAO.getById(traineeId)).thenReturn(trainee);

        // Act
        Trainee deletedTrainee = traineeService.deleteTrainee(traineeId);

        // Assert
        Mockito.verify(userDAO).removeById(userId);
        Mockito.verify(traineeDAO).removeById(traineeId);
        assertEquals(trainee, deletedTrainee);
    }

    @Test
    public void givenTraineeId_whenGetTraineeById_thenTraineeIsRetrieved() {
        // Arrange
        int traineeId = 1;
        Trainee trainee = new Trainee(traineeId, new Date(), "123 Main St", 1);
        when(traineeDAO.getById(traineeId)).thenReturn(trainee);

        // Act
        Trainee retrievedTrainee = traineeService.getTraineeById(traineeId);

        // Assert
        assertEquals(traineeId, retrievedTrainee.getId());
    }
}
