package spring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.Trainee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TraineeTest {
    private Trainee systemUnderTest;

    public TraineeTest() {
    }

    @Before
    public void setUp() throws ParseException {
        // Arrange
        // Set a date of birth
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date DOB = sdf.parse("1990-05-20");
        // Create an instance of Trainee
        systemUnderTest = new Trainee(1, DOB, "123 Gym St", 101);
    }

    @Test
    public void givenTraineeWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        int expected = 1;
        int actual = systemUnderTest.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        int newId = 2;
        systemUnderTest.setId(newId);

        // Assert
        int expected = 2;
        int actual = systemUnderTest.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void givenTraineeWithDateOfBirth_whenGetDateOfBirth_thenShouldReturnDateOfBirth() throws ParseException {
        // Act and Assert
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date expected = sdf.parse("1990-05-20");
        Date actual = systemUnderTest.getDateOfBirth();
        assertEquals(expected, actual);
    }

    @Test
    public void givenNewDateOfBirth_whenSetDateOfBirth_thenShouldUpdateDateOfBirth() throws ParseException {
        // Act
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDateOfBirth = sdf.parse("1990-05-21");
        systemUnderTest.setDateOfBirth(newDateOfBirth);

        // Assert
        Date actual = systemUnderTest.getDateOfBirth();
        assertEquals(newDateOfBirth, actual);
    }

    @Test
    public void givenTraineeWithAddress_whenGetAddress_thenShouldReturnAddress() {
        // Act and Assert
        String expected = "123 Gym St";
        String actual = systemUnderTest.getAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void givenNewAddress_whenSetAddress_thenShouldUpdateAddress() {
        // Act
        String newAddress = "456 Fitness Ave";
        systemUnderTest.setAddress(newAddress);

        // Assert
        String actual = systemUnderTest.getAddress();
        assertEquals(newAddress, actual);
    }

    @Test
    public void givenTraineeWithUserId_whenGetUserId_thenShouldReturnUserId() {
        // Act and Assert
        int expected = 101;
        int actual = systemUnderTest.getUserId();
        assertEquals(expected, actual);
    }

    @Test
    public void givenNewUserId_whenSetUserId_thenShouldUpdateUserId() {
        // Act
        int newUserId = 102;
        systemUnderTest.setUserId(newUserId);

        // Assert
        int actual = systemUnderTest.getUserId();
        assertEquals(newUserId, actual);
    }

    @Test
    public void givenTrainee_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expected = "Trainee{id=1, dateOfBirth=" + systemUnderTest.getDateOfBirth() + ", address='123 Gym St', userId=101}";
        String actual = systemUnderTest.toString();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testDefaultConstructor_shouldCreateTraineeObject() {
        // Act
        Trainee defaultTrainee = new Trainee();

        // Assert
        assertNotNull(defaultTrainee);
    }


}
