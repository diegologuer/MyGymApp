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
    private Trainee trainee;

    public TraineeTest() {
    }

    @Before
    public void setUp() throws ParseException {
        // Arrange
        // Set a date of birth
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date DOB = sdf.parse("1990-05-20");
        // Create an instance of Trainee
        trainee = new Trainee(1, DOB, "123 Gym St", 101);
    }

    @Test
    public void givenTraineeWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        assertEquals(1, trainee.getId());
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        trainee.setId(2);

        // Assert
        assertEquals(2, trainee.getId());
    }

    @Test
    public void givenTraineeWithDateOfBirth_whenGetDateOfBirth_thenShouldReturnDateOfBirth() throws ParseException {
        // Act and Assert
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDateOfBirth = sdf.parse("1990-05-20");
        assertEquals(newDateOfBirth, trainee.getDateOfBirth());
    }

    @Test
    public void givenNewDateOfBirth_whenSetDateOfBirth_thenShouldUpdateDateOfBirth() throws ParseException {
        // Act
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDateOfBirth = sdf.parse("1990-05-21");
        trainee.setDateOfBirth(newDateOfBirth);

        // Assert
        assertEquals(newDateOfBirth, trainee.getDateOfBirth());
    }

    @Test
    public void givenTraineeWithAddress_whenGetAddress_thenShouldReturnAddress() {
        // Act and Assert
        assertEquals("123 Gym St", trainee.getAddress());
    }

    @Test
    public void givenNewAddress_whenSetAddress_thenShouldUpdateAddress() {
        // Act
        trainee.setAddress("456 Fitness Ave");

        // Assert
        assertEquals("456 Fitness Ave", trainee.getAddress());
    }

    @Test
    public void givenTraineeWithUserId_whenGetUserId_thenShouldReturnUserId() {
        // Act and Assert
        assertEquals(101, trainee.getUserId());
    }

    @Test
    public void givenNewUserId_whenSetUserId_thenShouldUpdateUserId() {
        // Act
        trainee.setUserId(102);

        // Assert
        assertEquals(102, trainee.getUserId());
    }

    @Test
    public void givenTrainee_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedString = "Trainee{id=1, dateOfBirth=" + trainee.getDateOfBirth() + ", address='123 Gym St', userId=101}";

        // Assert
        assertEquals(expectedString, trainee.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateTraineeObject() {
        // Act
        Trainee defaultTrainee = new Trainee();

        // Assert
        assertNotNull(defaultTrainee);
    }

}
