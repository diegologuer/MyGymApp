package spring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
    private UserDAO userDAO;

    @Before
    public void setUp() {
        storage = mock(Storage.class);
        userDAO = new UserDAOImpl(storage);
    }

    @Test
    public void saveTest() {
        User user = new User(1, "John", "Doe", "John.Doe", "1234567890", true);
        when(storage.getUserMap()).thenReturn(new HashMap<>());
        int saveUserId = userDAO.save(user);
        assertEquals(1, saveUserId);
    }

    @Test
    public void testGetUserById() {
        User user = new User(1, "John", "Doe", "John.Doe", "password", true);
        when(storage.getUserMap()).thenReturn(mock(Map.class));
        when(storage.getUserMap().get(1)).thenReturn(user);
        User result = userDAO.getById(1);
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetUserByIdNotFound() {
        when(storage.getUserMap()).thenReturn(mock(Map.class));
        userDAO.getById(1);
    }

    @Test
    public void testRemoveByIdUserFound() {

        User user = new User(1, "John", "Doe", "John.Doe", "password", true);
        int userId = user.getId();
        when(storage.getUserMap()).thenReturn(new HashMap<>());
        userDAO.save(user);
        User removedUser = userDAO.removeById(userId);
        assertEquals(user, removedUser);
    }

    @Test
    public void testUsernameExists() {
        User user1 = new User(1, "John", "Doe", "John.Doe", "password", true);
        User user2 = new User(2, "Alice", "Smith", "Alice.Smith", "password", true);
        when(storage.getUserMap()).thenReturn(new HashMap<>());
        storage.getUserMap().put(1, user1);
        storage.getUserMap().put(2, user2);
        boolean exists = userDAO.usernameExists("John.Doe");
        assertTrue(exists);
        boolean doesNotExist = userDAO.usernameExists("NonExistingUsername");
        assertTrue(!doesNotExist);
    }

    @Test
    public void testNextAvailableId() {
        when(storage.nextAvailableUserId()).thenReturn(1);
        int result = userDAO.nextAvailableId();
        assertEquals(1, result);
        verify(storage).nextAvailableUserId();
    }
}

