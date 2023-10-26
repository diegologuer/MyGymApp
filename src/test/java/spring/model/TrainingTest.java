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
        // Arrange
        // Create an instance of Training to use in the tests
        training = new Training(1, "Cardio Kickboxing Class", new Date(), 101, 2, 60, 201);
    }

    @Test
    public void givenTrainingWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        assertEquals(1, training.getId());
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        training.setid(2);

        // Assert
        assertEquals(2, training.getId());
    }

    @Test
    public void givenTrainingWithTrainingName_whenGetTrainingName_thenShouldReturnTrainingName() {
        // Act and Assert
        assertEquals("Cardio Kickboxing Class", training.getTrainingName());
    }

    @Test
    public void givenNewTrainingName_whenSetTrainingName_thenShouldUpdateTrainingName() {
        // Act
        training.setTrainingName("Strength and Conditioning Workshop");

        // Assert
        assertEquals("Strength and Conditioning Workshop", training.getTrainingName());
    }

    @Test
    public void givenNewTrainingDate_whenSetTrainingDate_thenShouldUpdateTrainingDate() throws ParseException {
        // Act
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newTrainingDate = sdf.parse("2023-11-15");
        training.setTrainingDate(newTrainingDate);

        // Assert
        assertEquals(newTrainingDate, training.getTrainingDate());
    }

    @Test
    public void givenTrainingWithTraineeId_whenGetTraineeId_thenShouldReturnTraineeId() {
        // Act and Assert
        assertEquals(101, training.getTraineeId());
    }

    @Test
    public void givenNewTraineeId_whenSetTraineeId_thenShouldUpdateTraineeId() {
        // Act
        training.setTraineeId(102);

        // Assert
        assertEquals(102, training.getTraineeId());
    }

    @Test
    public void givenTrainingWithTrainingTypeId_whenGetTrainingTypeId_thenShouldReturnTrainingTypeId() {
        // Act and Assert
        assertEquals(2, training.getTrainingTypeId());
    }

    @Test
    public void givenNewTrainingTypeId_whenSetTrainingTypeId_thenShouldUpdateTrainingTypeId() {
        // Act
        training.setTrainingTypeId(3);

        // Assert
        assertEquals(3, training.getTrainingTypeId());
    }

    @Test
    public void givenTrainingWithTrainingDuration_whenGetTrainingDuration_thenShouldReturnTrainingDuration() {
        // Act and Assert
        assertEquals(60, training.getTrainingDuration());
    }

    @Test
    public void givenNewTrainingDuration_whenSetTrainingDuration_thenShouldUpdateTrainingDuration() {
        // Act
        training.setTrainingDuration(90);

        // Assert
        assertEquals(90, training.getTrainingDuration());
    }

    @Test
    public void givenTrainingWithTrainerId_whenGetTrainerId_thenShouldReturnTrainerId() {
        // Act and Assert
        assertEquals(201, training.getTrainerId());
    }

    @Test
    public void givenNewTrainerId_whenSetTrainerId_thenShouldUpdateTrainerId() {
        // Act
        training.setTrainerId(202);

        // Assert
        assertEquals(202, training.getTrainerId());
    }

    @Test
    public void givenTraining_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedString = "Training{id=1, trainingName='Cardio Kickboxing Class', trainingDate=" + training.getTrainingDate() + ", traineeId=101, trainingTypeId=2, trainingDuration=60, trainerId=201}";

        // Assert
        assertEquals(expectedString, training.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainingObject() {
        // Act
        Training defaultTraining = new Training();

        // Assert
        assertNotNull(defaultTraining);
    }

}
