package spring.service;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.spring.repository.user.UserDAO;
import org.spring.service.profile.CredentialsServiceImpl;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class CredentialsServiceImplTest {

    private CredentialsServiceImpl systemUnderTest;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() {
        userDAO = mock(UserDAO.class);
        systemUnderTest = new CredentialsServiceImpl(userDAO);
    }

    @Test
    public void givenValidNameAndLastName_whenGenerateUsername_thenUsernameIsGenerated() {
        // Arrange
        String name = "John";
        String lastName = "Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(false);

        // Act
        String actualUsername = systemUnderTest.generateUsername(name, lastName);

        // Assert
        assertEquals("John.Doe", actualUsername);
    }

    @Test
    public void givenExistingUsername_whenGenerateUsername_thenGeneratedUsernameIsDifferent() {
        // Arrange
        String name = "John";
        String lastName = "Doe";
        String expectedUsername = "John.Doe";
        when(userDAO.usernameExists(anyString())).thenReturn(true, false);

        // Act
        String actualUsername = systemUnderTest.generateUsername(name, lastName);

        // Assert
        assertNotEquals(expectedUsername, actualUsername);
    }

    @Test
    public void givenGeneratePassword_whenGeneratePassword_thenPasswordIsTenCharactersLong() {
        // Act
        int expectedPasswordLength = 10;
        String actualPassword = systemUnderTest.generatePassword();

        // Assert
        assertNotNull(actualPassword);
        assertEquals(expectedPasswordLength, actualPassword.length());
    }
}

