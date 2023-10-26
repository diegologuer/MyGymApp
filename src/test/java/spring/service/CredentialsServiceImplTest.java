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
        String name = "John";
        String lastName = "Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(false);
        String username = credentialsService.generateUsername(name, lastName);
        assertEquals("John.Doe", username);
    }

    @Test
    public void givenExistingUsername_whenGenerateUsername_thenGeneratedUsernameIsDifferent() {
        String name = "John";
        String lastName = "Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(true, false);
        String username = credentialsService.generateUsername(name, lastName);
        assertNotEquals("John.Doe", username);
    }

    @Test
    public void givenGeneratePassword_whenGeneratePassword_thenPasswordIsTenCharactersLong() {
        String password = credentialsService.generatePassword();
        assertNotNull(password);
        assertEquals(10, password.length());
    }
}

