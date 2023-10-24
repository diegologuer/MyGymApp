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

    //Set a date of birth
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date DOB = sdf.parse("1990-05-20");

    public TraineeTest() throws ParseException {
    }

    @Before
    public void setUp() {
        // Create an instance of Trainee
        trainee = new Trainee(1, DOB, "123 Gym St", 101);
    }

    @Test
    public void testGetId() {
        assertEquals(1, trainee.getID());
    }

    @Test
    public void testSetId() {
        trainee.setId(2);
        assertEquals(2, trainee.getID());
    }


    @Test
    public void testSetDateOfBirth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDateOfBirth = sdf.parse("1990-05-20");
        trainee.setDateOfBirth(newDateOfBirth);
        assertEquals(newDateOfBirth, trainee.getDateOfBirth());
    }

    @Test
    public void testGetAddress() {
        assertEquals("123 Gym St", trainee.getAddress());
    }

    @Test
    public void testSetAddress() {
        trainee.setAddress("456 Fitness Ave");
        assertEquals("456 Fitness Ave", trainee.getAddress());
    }

    @Test
    public void testGetUserId() {
        assertEquals(101, trainee.getUserId());
    }

    @Test
    public void testSetUserId() {
        trainee.setUserId(102);
        assertEquals(102, trainee.getUserId());
    }

    @Test
    public void testToString() {
        String expectedString = "Trainee{id=1, dateOfBirth=" + trainee.getDateOfBirth() + ", address='123 Gym St', userId=101}";
        assertEquals(expectedString, trainee.toString());
    }

    @Test
    public void testDefaultConstructor() {
        Trainee defaultTrainee = new Trainee();
        assertNotNull(defaultTrainee);
    }
}
