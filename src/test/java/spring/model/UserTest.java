package spring.model;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.User;

import static org.junit.Assert.*;

public class UserTest {

    private User systemUnderTest;

    @Before
    public void setUp() {
        // Arrange
        // Create an instance of User to use in the tests
        systemUnderTest = new User(1, "John", "Doe", "John.Doe", "aB3#dFg$12", true);
    }

    @Test
    public void givenUserWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        int expectedId = 1;
        int actualId = systemUnderTest.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        int newId = 2;
        systemUnderTest.setId(newId);

        // Assert
        int actualId = systemUnderTest.getId();
        assertEquals(newId, actualId);
    }

    @Test
    public void givenUserWithFirstName_whenGetFirstName_thenShouldReturnFirstName() {
        // Act and Assert
        String expectedFirstName = "John";
        String actualFirstName = systemUnderTest.getFirstName();
        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    public void givenNewFirstName_whenSetFirstName_thenShouldUpdateFirstName() {
        // Act
        String newFirstName = "Alice";
        systemUnderTest.setFirstName(newFirstName);

        // Assert
        String actualFirstName = systemUnderTest.getFirstName();
        assertEquals(newFirstName, actualFirstName);
    }

    @Test
    public void givenUserWithLastName_whenGetLastName_thenShouldReturnLastName() {
        // Act and Assert
        String expectedLastName = "Doe";
        String actualLastName = systemUnderTest.getLastName();
        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    public void givenNewLastName_whenSetLastName_thenShouldUpdateLastName() {
        // Act
        String newLastName = "Smith";
        systemUnderTest.setLastName(newLastName);

        // Assert
        String actualLastName = systemUnderTest.getLastName();
        assertEquals(newLastName, actualLastName);
    }

    @Test
    public void givenUserWithUsername_whenGetUsername_thenShouldReturnUsername() {
        // Act and Assert
        String expectedUsername = "John.Doe";
        String actualUsername = systemUnderTest.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void givenNewUsername_whenSetUsername_thenShouldUpdateUsername() {
        // Act
        String newUsername = "Alice.Smith";
        systemUnderTest.setUsername(newUsername);

        // Assert
        String actualUsername = systemUnderTest.getUsername();
        assertEquals(newUsername, actualUsername);
    }

    @Test
    public void givenUserWithPassword_whenGetPassword_thenShouldReturnPassword() {
        // Act and Assert
        String expectedPassword = "aB3#dFg$12";
        String actualPassword = systemUnderTest.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void givenNewPassword_whenSetPassword_thenShouldUpdatePassword() {
        // Act
        String newPassword = "xY7@kLp$34";
        systemUnderTest.setPassword(newPassword);

        // Assert
        String actualPassword = systemUnderTest.getPassword();
        assertEquals(newPassword, actualPassword);
    }

    @Test
    public void givenUserWithIsActive_whenGetIsActive_thenShouldReturnIsActive() {
        // Act and Assert
        boolean expectedIsActive = true;
        boolean actualIsActive = systemUnderTest.getIsActive();
        assertEquals(expectedIsActive, actualIsActive);
    }

    @Test
    public void givenNewIsActive_whenSetIsActive_thenShouldUpdateIsActive() {
        // Act
        boolean newIsActive = false;
        systemUnderTest.setIsActive(newIsActive);

        // Assert
        boolean actualIsActive = systemUnderTest.getIsActive();
        assertEquals(newIsActive, actualIsActive);
    }

    @Test
    public void givenUser_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedString = "User{id=1, firstName='John', lastName='Doe', username='John.Doe', password='aB3#dFg$12', isActive=true}";
        String actualString = systemUnderTest.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }

    @Test
    public void testDefaultConstructor_shouldCreateUserObject() {
        // Act
        User defaultUser = new User();

        // Assert
        assertNotNull(defaultUser);
    }
}
