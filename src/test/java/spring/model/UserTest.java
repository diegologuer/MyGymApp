package spring.model;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.User;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        // Arrange
        // Create an instance of User to use in the tests
        user = new User(1, "John", "Doe", "John.Doe", "aB3#dFg$12", true);
    }

    @Test
    public void givenUserWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        assertEquals(1, user.getId());
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        user.setId(2);

        // Assert
        assertEquals(2, user.getId());
    }

    @Test
    public void givenUserWithFirstName_whenGetFirstName_thenShouldReturnFirstName() {
        // Act and Assert
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void givenNewFirstName_whenSetFirstName_thenShouldUpdateFirstName() {
        // Act
        user.setFirstName("Alice");

        // Assert
        assertEquals("Alice", user.getFirstName());
    }

    @Test
    public void givenUserWithLastName_whenGetLastName_thenShouldReturnLastName() {
        // Act and Assert
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void givenNewLastName_whenSetLastName_thenShouldUpdateLastName() {
        // Act
        user.setLastName("Smith");

        // Assert
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void givenUserWithUsername_whenGetUsername_thenShouldReturnUsername() {
        // Act and Assert
        assertEquals("John.Doe", user.getUsername());
    }

    @Test
    public void givenNewUsername_whenSetUsername_thenShouldUpdateUsername() {
        // Act
        user.setUsername("Alice.Smith");

        // Assert
        assertEquals("Alice.Smith", user.getUsername());
    }

    @Test
    public void givenUserWithPassword_whenGetPassword_thenShouldReturnPassword() {
        // Act and Assert
        assertEquals("aB3#dFg$12", user.getPassword());
    }

    @Test
    public void givenNewPassword_whenSetPassword_thenShouldUpdatePassword() {
        // Act
        user.setPassword("xY7@kLp$34");

        // Assert
        assertEquals("xY7@kLp$34", user.getPassword());
    }

    @Test
    public void givenUserWithIsActive_whenGetIsActive_thenShouldReturnIsActive() {
        // Act and Assert
        assertTrue(user.getIsActive());
    }

    @Test
    public void givenNewIsActive_whenSetIsActive_thenShouldUpdateIsActive() {
        // Act
        user.setIsActive(false);

        // Assert
        assertFalse(user.getIsActive());
    }

    @Test
    public void givenUser_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedString = "User{id=1, firstName='John', lastName='Doe', username='John.Doe', password='aB3#dFg$12', isActive=true";

        // Assert
        assertEquals(expectedString, user.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateUserObject() {
        // Act
        User defaultUser = new User();

        // Assert
        assertNotNull(defaultUser);
    }

}
