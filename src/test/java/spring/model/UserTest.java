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
    public void givenUserWithId_whenGetId_thenShouldReturnId() {
        assertEquals(1, user.getId());
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void givenUserWithFirstName_whenGetFirstName_thenShouldReturnFirstName() {
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void givenNewFirstName_whenSetFirstName_thenShouldUpdateFirstName() {
        user.setFirstName("Alice");
        assertEquals("Alice", user.getFirstName());
    }

    @Test
    public void givenUserWithLastName_whenGetLastName_thenShouldReturnLastName() {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void givenNewLastName_whenSetLastName_thenShouldUpdateLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void givenUserWithUsername_whenGetUsername_thenShouldReturnUsername() {
        assertEquals("John.Doe", user.getUsername());
    }

    @Test
    public void givenNewUsername_whenSetUsername_thenShouldUpdateUsername() {
        user.setUsername("Alice.Smith");
        assertEquals("Alice.Smith", user.getUsername());
    }

    @Test
    public void givenUserWithPassword_whenGetPassword_thenShouldReturnPassword() {
        assertEquals("aB3#dFg$12", user.getPassword());
    }

    @Test
    public void givenNewPassword_whenSetPassword_thenShouldUpdatePassword() {
        user.setPassword("xY7@kLp$34");
        assertEquals("xY7@kLp$34", user.getPassword());
    }

    @Test
    public void givenUserWithIsActive_whenGetIsActive_thenShouldReturnIsActive() {
        assertTrue(user.getIsActive());
    }

    @Test
    public void givenNewIsActive_whenSetIsActive_thenShouldUpdateIsActive() {
        user.setIsActive(false);
        assertFalse(user.getIsActive());
    }

    @Test
    public void givenUser_whenToString_thenShouldReturnStringRepresentation() {
        String expectedString = "User{id=1, firstName='John', lastName='Doe', username='John.Doe', password='aB3#dFg$12', isActive=true}";
        assertEquals(expectedString, user.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateUserObject() {
        User defaultUser = new User();
        assertNotNull(defaultUser);
    }
}
