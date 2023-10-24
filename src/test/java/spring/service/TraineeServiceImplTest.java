package spring.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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
        traineeDAO = mock(TraineeDAOImpl.class);
        credentialsService = mock(CredentialsService.class);
        userDAO = mock(UserDAOImpl.class);
        traineeService = new TraineeServiceImpl(traineeDAO, credentialsService, userDAO);
    }

    @Test
    public void testCreateTrainee() {
        // Prepare data
        String name = "John";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String address = "123 Main St";

        // Define the behavior of the credentialsService.generateUsername and credentialsService.generatePassword methods
        when(credentialsService.generateUsername(name, lastName)).thenReturn("John.Doe");
        when(credentialsService.generatePassword()).thenReturn("randomPassword");

        // Define the behavior of the traineeDAO.nextAvailableId and userDAO.nextAvailableId methods
        when(traineeDAO.nextAvailableId()).thenReturn(1);
        when(userDAO.nextAvailableId()).thenReturn(1);

        // Define the behavior of the traineeDAO.save and userDAO.save methods
        when(traineeDAO.save(any(Trainee.class))).thenReturn(1);
        when(userDAO.save(any(User.class))).thenReturn(1);

        // Create the trainee
        int traineeId = traineeService.createTrainee(name, lastName, dateOfBirth, address);

        // Verify that the trainee was created and returned with the correct ID
        assertEquals(1, traineeId);
    }

    @Test
    public void testUpdateTrainee() {
        // Prepare data
        int traineeId = 1;
        Date dateOfBirth = new Date();
        String address = "456 Elm St";

        // Mock the behavior of the traineeDAO.getById method
        Trainee trainee = new Trainee(traineeId, new Date(), "123 Main St", 1);
        when(traineeDAO.getById(traineeId)).thenReturn(trainee);

        // Update the trainee
        Trainee updatedTrainee = traineeService.updateTrainee(traineeId, dateOfBirth, address);

        // Verify that the trainee was updated and returned with the correct data
        assertEquals(dateOfBirth, updatedTrainee.getDateOfBirth());
        assertEquals(address, updatedTrainee.getAddress());
    }

    @Test
    public void testDeleteTrainee() {
        // Prepare data
        int traineeId = 1;
        int userId = 1;

        // Mock the behavior of the traineeDAO.getById method
        Trainee trainee = new Trainee(traineeId, new Date(), "123 Main St", userId);
        when(traineeDAO.getById(traineeId)).thenReturn(trainee);

        // Define the behavior of the userDAO.removeById method
        doNothing().when(userDAO).removeById(userId);

        // Define the behavior of the traineeDAO.removeById method
        when(traineeDAO.removeById(traineeId)).thenReturn(trainee);

        // Delete the trainee
        Trainee deletedTrainee = traineeService.deleteTrainee(traineeId);

        // Verify that the trainee and associated user were deleted
        assertNotNull(deletedTrainee);
    }

    @Test
    public void testGetTraineeById() {
        // Prepare data
        int traineeId = 1;

        // Mock the behavior of the traineeDAO.getById method
        Trainee trainee = new Trainee(traineeId, new Date(), "123 Main St", 1);
        when(traineeDAO.getById(traineeId)).thenReturn(trainee);

        // Get the trainee by ID
        Trainee retrievedTrainee = traineeService.getTraineeById(traineeId);

        // Verify that the correct trainee was retrieved
        assertEquals(traineeId, retrievedTrainee.getID());
    }
}
