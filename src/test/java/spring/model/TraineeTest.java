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
        //Set a date of birth
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date DOB = sdf.parse("1990-05-20");
        // Create an instance of Trainee
        trainee = new Trainee(1, DOB, "123 Gym St", 101);
    }

    @Test
    public void givenTraineeWithId_whenGetId_thenShouldReturnId() {
        assertEquals(1, trainee.getId());
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        trainee.setId(2);
        assertEquals(2, trainee.getId());
    }

    @Test
    public void givenTraineeWithDateOfBirth_whenGetDateOfBirth_thenShouldReturnDateOfBirth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDateOfBirth = sdf.parse("1990-05-20");
        assertEquals(newDateOfBirth, trainee.getDateOfBirth());
    }

    @Test
    public void givenNewDateOfBirth_whenSetDateOfBirth_thenShouldUpdateDateOfBirth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDateOfBirth = sdf.parse("1990-05-21");
        trainee.setDateOfBirth(newDateOfBirth);
        assertEquals(newDateOfBirth, trainee.getDateOfBirth());
    }

    @Test
    public void givenTraineeWithAddress_whenGetAddress_thenShouldReturnAddress() {
        assertEquals("123 Gym St", trainee.getAddress());
    }

    @Test
    public void givenNewAddress_whenSetAddress_thenShouldUpdateAddress() {
        trainee.setAddress("456 Fitness Ave");
        assertEquals("456 Fitness Ave", trainee.getAddress());
    }

    @Test
    public void givenTraineeWithUserId_whenGetUserId_thenShouldReturnUserId() {
        assertEquals(101, trainee.getUserId());
    }

    @Test
    public void givenNewUserId_whenSetUserId_thenShouldUpdateUserId() {
        trainee.setUserId(102);
        assertEquals(102, trainee.getUserId());
    }

    @Test
    public void givenTrainee_whenToString_thenShouldReturnStringRepresentation() {
        String expectedString = "Trainee{id=1, dateOfBirth=" + trainee.getDateOfBirth() + ", address='123 Gym St', userId=101}";
        assertEquals(expectedString, trainee.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateTraineeObject() {
        Trainee defaultTrainee = new Trainee();
        assertNotNull(defaultTrainee);
    }
}
