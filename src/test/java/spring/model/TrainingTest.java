package spring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.Training;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainingTest {
    private Training training;

    @Before
    public void setUp() {
        // Create an instance of Training to use in the tests
        training = new Training(1, "Cardio Kickboxing Class", new Date(), 101, 2, 60, 201);
    }

    @Test
    public void testGetId() {
        assertEquals(1, training.getid());
    }

    @Test
    public void testSetId() {
        training.setid(2);
        assertEquals(2, training.getid());
    }

    @Test
    public void testGetTrainingName() {
        assertEquals("Cardio Kickboxing Class", training.getTrainingName());
    }

    @Test
    public void testSetTrainingName() {
        training.setTrainingName("Strength and Conditioning Workshop");
        assertEquals("Strength and Conditioning Workshop", training.getTrainingName());
    }

    @Test
    public void testSetTrainingDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newTrainingDate = sdf.parse("2023-11-15");
        training.setTrainingDate(newTrainingDate);
        assertEquals(newTrainingDate, training.getTrainingDate());
    }

    @Test
    public void testGetTraineeId() {
        assertEquals(101, training.getTraineeId());
    }

    @Test
    public void testSetTraineeId() {
        training.setTraineeId(102);
        assertEquals(102, training.getTraineeId());
    }

    @Test
    public void testGetTrainingTypeId() {
        assertEquals(2, training.getTrainingTypeId());
    }

    @Test
    public void testSetTrainingTypeId() {
        training.setTrainingTypeId(3);
        assertEquals(3, training.getTrainingTypeId());
    }

    @Test
    public void testGetTrainingDuration() {
        assertEquals(60, training.getTrainingDuration());
    }

    @Test
    public void testSetTrainingDuration() {
        training.setTrainingDuration(90);
        assertEquals(90, training.getTrainingDuration());
    }

    @Test
    public void testGetTrainerId() {
        assertEquals(201, training.getTrainerId());
    }

    @Test
    public void testSetTrainerId() {
        training.setTrainerId(202);
        assertEquals(202, training.getTrainerId());
    }

    @Test
    public void testToString() {
        String expectedString = "Training{id=1, trainingName='Cardio Kickboxing Class', trainingDate=" + training.getTrainingDate() + ", traineeId=101, trainingTypeId=2, trainingDuration=60, trainerId=201}";
        assertEquals(expectedString, training.toString());
    }

    @Test
    public void testDefaultConstructor() {
        Training defaultTraining = new Training();
        assertNotNull(defaultTraining);
    }
}
