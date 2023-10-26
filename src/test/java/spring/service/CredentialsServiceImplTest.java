package spring.service;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.spring.repository.user.UserDAO;
import org.spring.service.profile.CredentialsServiceImpl;
import org.mockito.Mock;

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
    public void givenValidNameAndLastName_whenGenerateUsername_thenUsernameIsGenerated() {
        // Arrange
        String name = "John";
        String lastName = "Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(false);

        // Act
        String username = credentialsService.generateUsername(name, lastName);

        // Assert
        assertEquals("John.Doe", username);
    }

    @Test
    public void givenExistingUsername_whenGenerateUsername_thenGeneratedUsernameIsDifferent() {
        // Arrange
        String name = "John";
        String lastName = "Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(true, false);

        // Act
        String username = credentialsService.generateUsername(name, lastName);

        // Assert
        assertNotEquals("John.Doe", username);
    }

    @Test
    public void givenGeneratePassword_whenGeneratePassword_thenPasswordIsTenCharactersLong() {
        // Act
        String password = credentialsService.generatePassword();

        // Assert
        assertNotNull(password);
        assertEquals(10, password.length());
    }
}

