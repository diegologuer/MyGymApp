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
    void whenGettingUserIdThenUserIdIsReturned() {
        // When
        int actualUserId = sut.getId();

        // Then
        assertEquals(1, actualUserId);
    }

    @Test
    void whenSettingUserIdThenUserIdIsSet() {
        // Given
        int expectedUserId = 123;

        // When
        sut.setId(expectedUserId);

        // Then
        assertEquals(expectedUserId, sut.getId());
    }

    @Test
    void whenGettingFirstNameThenFirstNameIsReturned() {
        // When
        String actualFirstName = sut.getFirstName();

        // Then
        assertEquals("John", actualFirstName);
    }

    @Test
    void whenSettingFirstNameThenFirstNameIsSet() {
        // Given
        String expectedFirstName = "Jane";

        // When
        sut.setFirstName(expectedFirstName);

        // Then
        assertEquals(expectedFirstName, sut.getFirstName());
    }

    @Test
    void whenGettingLastNameThenLastNameIsReturned() {
        // When
        String actualLastName = sut.getLastName();

        // Then
        assertEquals("Doe", actualLastName);
    }

    @Test
    void whenSettingLastNameThenLastNameIsSet() {
        // Given
        String expectedLastName = "Smith";

        // When
        sut.setLastName(expectedLastName);

        // Then
        assertEquals(expectedLastName, sut.getLastName());
    }

    @Test
    void whenGettingUsernameThenUsernameIsReturned() {
        // When
        String actualUsername = sut.getUsername();

        // Then
        assertEquals("john_doe", actualUsername);
    }

    @Test
    void whenSettingUsernameThenUsernameIsSet() {
        // Given
        String expectedUsername = "jane_smith";

        // When
        sut.setUsername(expectedUsername);

        // Then
        assertEquals(expectedUsername, sut.getUsername());
    }

    @Test
    void whenGettingPasswordThenPasswordIsReturned() {
        // When
        String actualPassword = sut.getPassword();

        // Then
        assertEquals("password123", actualPassword);
    }

    @Test
    void whenSettingPasswordThenPasswordIsSet() {
        // Given
        String expectedPassword = "new_password";

        // When
        sut.setPassword(expectedPassword);

        // Then
        assertEquals(expectedPassword, sut.getPassword());
    }

    @Test
    void whenGettingIsActiveThenIsActiveIsReturned() {
        // When
        boolean actualIsActive = sut.getIsActive();

        // Then
        assertEquals(true, actualIsActive);
    }

    @Test
    void whenSettingIsActiveThenIsActiveIsSet() {
        // When
        sut.setIsActive(false);

        // Then
        assertEquals(false, sut.getIsActive());
    }

    @Test
    void whenCallingUserToStringThenCorrectStringIsReturned() {
        // Given
        String expectedToString = "User{id=1, firstName='John', lastName='Doe', username='john_doe', password='password123', isActive=true}";

        // When
        String actualToString = sut.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }
}