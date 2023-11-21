package org.spring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User sut;

    @BeforeEach
    public void setUp() {
        sut = new User(1, "John", "Doe", "john_doe", "password123", true);
    }

    @Test
    void getUserId() {
        // Act
        int actualUserId = sut.getId();

        // Assert
        assertEquals(1, actualUserId);
    }

    @Test
    void setUserId() {
        // Arrange
        int expectedUserId = 123;

        // Act
        sut.setId(expectedUserId);

        // Assert
        assertEquals(expectedUserId, sut.getId());
    }

    @Test
    void getFirstName() {
        // Act
        String actualFirstName = sut.getFirstName();

        // Assert
        assertEquals("John", actualFirstName);
    }

    @Test
    void setFirstName() {
        // Arrange
        String expectedFirstName = "Jane";

        // Act
        sut.setFirstName(expectedFirstName);

        // Assert
        assertEquals(expectedFirstName, sut.getFirstName());
    }

    @Test
    void getLastName() {
        // Act
        String actualLastName = sut.getLastName();

        // Assert
        assertEquals("Doe", actualLastName);
    }

    @Test
    void setLastName() {
        // Arrange
        String expectedLastName = "Smith";

        // Act
        sut.setLastName(expectedLastName);

        // Assert
        assertEquals(expectedLastName, sut.getLastName());
    }

    @Test
    void getUsername() {
        // Act
        String actualUsername = sut.getUsername();

        // Assert
        assertEquals("john_doe", actualUsername);
    }

    @Test
    void setUsername() {
        // Arrange
        String expectedUsername = "jane_smith";

        // Act
        sut.setUsername(expectedUsername);

        // Assert
        assertEquals(expectedUsername, sut.getUsername());
    }

    @Test
    void getPassword() {
        // Act
        String actualPassword = sut.getPassword();

        // Assert
        assertEquals("password123", actualPassword);
    }

    @Test
    void setPassword() {
        // Arrange
        String expectedPassword = "new_password";

        // Act
        sut.setPassword(expectedPassword);

        // Assert
        assertEquals(expectedPassword, sut.getPassword());
    }

    @Test
    void getIsActive() {
        // Act
        boolean actualIsActive = sut.getIsActive();

        // Assert
        assertEquals(true, actualIsActive);
    }

    @Test
    void setIsActive() {
        // Act
        sut.setIsActive(false);

        // Assert
        assertEquals(false, sut.getIsActive());
    }

    @Test
    void testUserToString() {
        // Arrange
        String expectedToString = "User{id=1, firstName='John', lastName='Doe', username='john_doe', password='password123', isActive=true}";

        // Act
        String actualToString = sut.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
}