package spring.model;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.User;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        // Create an instance of User to use in the tests
        user = new User(1, "John", "Doe", "John.Doe", "aB3#dFg$12", true);
    }

    @Test
    public void testGetId() {
        assertEquals(1, user.getId());
    }

    @Test
    public void testSetId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        user.setFirstName("Alice");
        assertEquals("Alice", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testSetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testGetUsername() {
        assertEquals("John.Doe", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("Alice.Smith");
        assertEquals("Alice.Smith", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("aB3#dFg$12", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("xY7@kLp$34");
        assertEquals("xY7@kLp$34", user.getPassword());
    }

    @Test
    public void testGetIsActive() {
        assertTrue(user.getIsActive());
    }

    @Test
    public void testSetIsActive() {
        user.setIsActive(false);
        assertFalse(user.getIsActive());
    }

    @Test
    public void testToString() {
        String expectedString = "User{id=1, firstName='John', lastName='Doe', username='John.Doe', password='aB3#dFg$12', isActive=true}";
        assertEquals(expectedString, user.toString());
    }

    @Test
    public void testDefaultConstructor() {
        User defaultUser = new User();
        assertNotNull(defaultUser);
    }
}
