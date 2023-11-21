package org.spring.service.profile;

import com.sun.jdi.event.MethodExitEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.User;
import org.spring.repository.user.UserDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CredentialsServiceImplTest {

    @Mock
    UserDAO userDAO;

    @InjectMocks
    CredentialsServiceImpl sut;

    @Test
    void givenValidArgs_WhenGeneratingUsernameAndUserNameDoesNotPreviouslyExists_ThenUsernameIsGenerated() {
        //Arrange
        String name = "John";
        String lastname = "Doe";
        String expectedUsername = "John.Doe";
        when(userDAO.usernameExists(expectedUsername)).thenReturn(false);

        //Act
        String actualUsername = sut.generateUsername(name, lastname);

        //Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void givenValidArgs_WhenGeneratingUsernameAndUserNamePreviouslyExists_ThenUsernameIsGenerated() {
        //Arrange
        String name = "John";
        String lastname = "Doe";
        String existingUsername = "John.Doe";
        String expectedUsername = "John.Doe1";
        when(userDAO.usernameExists(existingUsername)).thenReturn(true);
        when(userDAO.usernameExists(expectedUsername)).thenReturn(false);

        //Act
        String actualUsername = sut.generateUsername(name, lastname);

        //Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void whenGeneratingPassword_PasswordIsGenerated() {

        //Act
        String password = sut.generatePassword();

        //Assert
        assertEquals(10, password.length());
        assertTrue(password.matches("[A-Za-z0-9!@#\\$%^&*\\(\\)_+\\-\\=\\[\\]\\{\\}\\|;:,.<>?]+"));
    }

    @Test
    void whenGeneratingPasswordMultipleTimes_PasswordIsAlwaysDifferent() {

        //Act
        String password1 = sut.generatePassword();
        String password2 = sut.generatePassword();

        //Assert
        assertNotEquals(password1, password2);
    }

    @Test
    void givenValidArgs_WhenCreatingPersonProfile_ThenPersonProfileIsCreated() {
        //Arrange
        String name = "John";
        String lastname = "Doe";
        int expectedUserId = 17;
        when(userDAO.usernameExists(any())).thenReturn(false);
        when(userDAO.nextAvailableId()).thenReturn(expectedUserId);

        //Act
        int actualUserId = sut.createPersonProfile(name, lastname);

        //Assert
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void givenInvalidArgs_WhenCreatingPersonProfile_ThenThrowsException() {
        //Arrange
        String name = null;
        String lastname = "";

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> sut.createPersonProfile(name, lastname));
    }
}