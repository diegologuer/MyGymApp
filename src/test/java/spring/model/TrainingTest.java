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
    public void givenTrainingWithId_whenGetId_thenShouldReturnId() {
        assertEquals(1, training.getId());
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        training.setid(2);
        assertEquals(2, training.getId());
    }

    @Test
    public void givenTrainingWithTrainingName_whenGetTrainingName_thenShouldReturnTrainingName() {
        assertEquals("Cardio Kickboxing Class", training.getTrainingName());
    }

    @Test
    public void givenNewTrainingName_whenSetTrainingName_thenShouldUpdateTrainingName() {
        training.setTrainingName("Strength and Conditioning Workshop");
        assertEquals("Strength and Conditioning Workshop", training.getTrainingName());
    }

    @Test
    public void givenNewTrainingDate_whenSetTrainingDate_thenShouldUpdateTrainingDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newTrainingDate = sdf.parse("2023-11-15");
        training.setTrainingDate(newTrainingDate);
        assertEquals(newTrainingDate, training.getTrainingDate());
    }

    @Test
    public void givenTrainingWithTraineeId_whenGetTraineeId_thenShouldReturnTraineeId() {
        assertEquals(101, training.getTraineeId());
    }

    @Test
    public void givenNewTraineeId_whenSetTraineeId_thenShouldUpdateTraineeId() {
        training.setTraineeId(102);
        assertEquals(102, training.getTraineeId());
    }

    @Test
    public void givenTrainingWithTrainingTypeId_whenGetTrainingTypeId_thenShouldReturnTrainingTypeId() {
        assertEquals(2, training.getTrainingTypeId());
    }

    @Test
    public void givenNewTrainingTypeId_whenSetTrainingTypeId_thenShouldUpdateTrainingTypeId() {
        training.setTrainingTypeId(3);
        assertEquals(3, training.getTrainingTypeId());
    }

    @Test
    public void givenTrainingWithTrainingDuration_whenGetTrainingDuration_thenShouldReturnTrainingDuration() {
        assertEquals(60, training.getTrainingDuration());
    }

    @Test
    public void givenNewTrainingDuration_whenSetTrainingDuration_thenShouldUpdateTrainingDuration() {
        training.setTrainingDuration(90);
        assertEquals(90, training.getTrainingDuration());
    }

    @Test
    public void givenTrainingWithTrainerId_whenGetTrainerId_thenShouldReturnTrainerId() {
        assertEquals(201, training.getTrainerId());
    }

    @Test
    public void givenNewTrainerId_whenSetTrainerId_thenShouldUpdateTrainerId() {
        training.setTrainerId(202);
        assertEquals(202, training.getTrainerId());
    }

    @Test
    public void givenTraining_whenToString_thenShouldReturnStringRepresentation() {
        String expectedString = "Training{id=1, trainingName='Cardio Kickboxing Class', trainingDate=" + training.getTrainingDate() + ", traineeId=101, trainingTypeId=2, trainingDuration=60, trainerId=201}";
        assertEquals(expectedString, training.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainingObject() {
        Training defaultTraining = new Training();
        assertNotNull(defaultTraining);
    }
}
