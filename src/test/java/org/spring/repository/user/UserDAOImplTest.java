package org.spring.repository.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.model.Trainee;
import org.spring.model.User;
import org.spring.storage.Storage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDAOImplTest {

    @Mock
    Storage storage;

    @InjectMocks
    UserDAOImpl sut;

    @Test
    void GivenValidArgs_WhenSavingUser_ThenUserIsSaved() {
        // Arrange
        int expectedUserId = 12;
        User user = new User(expectedUserId,"John","Doe","John.Doe",
                "123456!@#$",true);
        Map<Integer, User> userMap = new HashMap<>();
        when(storage.getUserMap()).thenReturn(userMap);

        // Act
        int actualUserId = sut.save(user);

        // Assert
        assertEquals(expectedUserId, actualUserId);
        assertTrue(userMap.containsKey(expectedUserId));
        assertEquals(user, userMap.get(expectedUserId));
    }

    @Test
    void GivenNullArg_WhenSavingUser_ThenThrowsAnException() {
        // Act and Assert
        assertThrows(RuntimeException.class, () -> sut.save(null), "Error saving user");
    }

    @Test
    void GivenValidId_WhenGettingUserById_ThenUserIsReturned() {
        // Arrange
        int userId = 4;
        User expectedUser = new User(3,"John","Doe","John.Doe",
                "123456!@#$",true);
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(userId, expectedUser);
        when(storage.getUserMap()).thenReturn(userMap);

        // Act
        User actualUser = sut.getById(userId);

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void GivenNotValidId_WhenGettingUserById_ThenThrowsException() {
        // Arrange
        int userId = 4;
        when(storage.getUserMap()).thenReturn(new HashMap<>());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> sut.getById(userId));
    }

    @Test
    void GivenValidId_WhenRemovingUserById_ThenUserIsRemoved() {
        // Arrange
        int userId = 4;
        User expectedUser = new User(3,"John","Doe","John.Doe",
                "123456!@#$",true);
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(userId, expectedUser);
        when(storage.getUserMap()).thenReturn(userMap);

        // Act
        User actualUser = sut.removeById(userId);

        // Assert
        assertEquals(expectedUser, actualUser);
        assertFalse(userMap.containsKey(userId));
    }

    @Test
    void GivenNotValidId_WhenRemovingUserById_ThenThrowsException() {
        // Arrange
        int userId = 4;
        when(storage.getUserMap()).thenReturn(new HashMap<>());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> sut.removeById(userId));
    }

    @Test
    void GivenExistingUsername_WhenCheckingUsernameExists_ThenReturnsTrue() {
        // Arrange
        String existingUsername = "John.Doe";
        User user = new User(3,"John","Doe","John.Doe",
                "123456!@#$",true);
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(3, user);
        when(storage.getUserMap()).thenReturn(userMap);

        // Act
        boolean result = sut.usernameExists(existingUsername);

        // Assert
        assertTrue(result);
    }

    @Test
    void GivenNonExistingUsername_WhenCheckingUsernameExists_ThenReturnsFalse() {
        // Arrange
        String nonExistingUsername = "nonexistent";
        when(storage.getUserMap()).thenReturn(new HashMap<>());

        // Act
        boolean result = sut.usernameExists(nonExistingUsername);

        // Assert
        assertFalse(result);
    }

    @Test
    void whenRequestingNextAvailableId_ThenNextAvailableIdIsReturned() {
        // Arrange
        int expectedAvailableId = 12;
        when(storage.nextAvailableUserId()).thenReturn(expectedAvailableId);

        // Act
        int actualAvailableId = sut.nextAvailableId();

        // Assert
        assertEquals(expectedAvailableId, actualAvailableId);
    }
}