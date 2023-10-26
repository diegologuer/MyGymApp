package spring.repository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.spring.model.User;
import org.spring.repository.user.UserDAO;
import org.spring.repository.user.UserDAOImpl;
import org.spring.storage.Storage;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class UserDAOImplTest {
    private Storage storage;
    private UserDAO systemUnderTest;


    @Before
    public void setUp() {
        // Arrange
        storage = mock(Storage.class);
        systemUnderTest = new UserDAOImpl(storage);
    }

    @Test
    public void givenUser_whenSave_thenShouldReturnSavedUserId() {
        // Arrange
        int expectedUserId = 1;
        User user = new User(1, "John", "Doe", "John.Doe", "1234567890", true);
        when(storage.getUserMap()).thenReturn(new HashMap<>());

        // Act
        int actualUserId = systemUnderTest.save(user);

        // Assert
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void givenUserMapWithUser_whenGetUserById_thenShouldReturnUser() {
        // Arrange
        User expectedUser = new User(1, "John", "Doe", "John.Doe", "password", true);
        when(storage.getUserMap()).thenReturn(mock(Map.class));
        when(storage.getUserMap().get(1)).thenReturn(expectedUser);

        // Act
        User actualUser = systemUnderTest.getById(1);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenEmptyUserMap_whenGetUserById_thenShouldThrowNoSuchElementException() {
        // Arrange
        when(storage.getUserMap()).thenReturn(mock(Map.class));

        // Act and Assert
        systemUnderTest.getById(1);
    }

    @Test
    public void givenUser_whenRemoveById_thenShouldReturnRemovedUser() {
        // Arrange
        User expectedUser = new User(1, "John", "Doe", "John.Doe", "password", true);
        int userId = expectedUser.getId();
        when(storage.getUserMap()).thenReturn(new HashMap<>());
        systemUnderTest.save(expectedUser);

        // Act
        User actualUser = systemUnderTest.removeById(userId);

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void givenUserMapWithUsers_whenUsernameExists_thenShouldReturnTrueIfExists() {
        // Arrange
        User user1 = new User(1, "John", "Doe", "John.Doe", "password", true);
        User user2 = new User(2, "Alice", "Smith", "Alice.Smith", "password", true);
        when(storage.getUserMap()).thenReturn(new HashMap<>());
        storage.getUserMap().put(1, user1);
        storage.getUserMap().put(2, user2);

        // Act
        boolean exists = systemUnderTest.usernameExists("John.Doe");
        boolean doesNotExist = systemUnderTest.usernameExists("NonExistingUsername");

        // Assert
        assertTrue(exists);
        assertFalse(doesNotExist);
    }

    @Test
    public void givenNextAvailableUserId_whenNextAvailableId_thenShouldReturnNextAvailableId() {
        // Arrange
        int expectedAvailableId = 1;
        when(storage.nextAvailableUserId()).thenReturn(1);

        // Act
        int actualAvailableId = systemUnderTest.nextAvailableId();

        // Assert
        assertEquals(expectedAvailableId, actualAvailableId);
        verify(storage).nextAvailableUserId();
    }
}

