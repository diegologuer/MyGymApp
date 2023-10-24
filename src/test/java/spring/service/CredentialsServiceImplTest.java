package spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    public void testGenerateUsername() {
        String name = "John";
        String lastName = "Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(false);
        String username = credentialsService.generateUsername(name, lastName);
        assertEquals("John.Doe", username);
    }

    @Test
    public void testGenerateUsernameWithExistingUsername() {
        String name = "John";
        String lastName = "Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(true, false);
        String username = credentialsService.generateUsername(name, lastName);
        assertNotEquals("John.Doe", username);
    }

    @Test
    public void testGeneratePassword() {
        String password = credentialsService.generatePassword();
        assertNotNull(password);
        assertEquals(10, password.length());
    }
}

