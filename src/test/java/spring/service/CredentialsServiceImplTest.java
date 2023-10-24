package spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.spring.repository.user.UserDAO;
import org.spring.service.profile.CredentialsService;
import org.spring.service.profile.CredentialsServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CredentialsServiceImplTest {

    private CredentialsServiceImpl credentialsService;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO = mock(UserDAO.class);
        credentialsService = new CredentialsServiceImpl(userDAO);
    }

    @Test
    public void testGenerateUsername() {
        // Prepare data
        String name = "John";
        String lastName = "Doe";

        // Define the behavior of the userDAO.usernameExists method
        when(userDAO.usernameExists(anyString())).thenReturn(false);

        // Generate the username
        String username = credentialsService.generateUsername(name, lastName);

        // Verify that the username is as expected
        assertEquals("John.Doe", username);
    }

    @Test
    public void testGenerateUsernameWithExistingUsername() {
        // Prepare data
        String name = "John";
        String lastName = "Doe";

        // Define the behavior of the userDAO.usernameExists method to return true for the first call
        when(userDAO.usernameExists(anyString())).thenReturn(true, false);

        // Generate the username
        String username = credentialsService.generateUsername(name, lastName);

        // Verify that the username is unique by checking if it's modified
        assertNotEquals("John.Doe", username);
    }

    @Test
    public void testGeneratePassword() {
        // Generate a password
        String password = credentialsService.generatePassword();

        // Verify that the generated password is not null and has the correct length
        assertNotNull(password);
        assertEquals(10, password.length());
    }
}

