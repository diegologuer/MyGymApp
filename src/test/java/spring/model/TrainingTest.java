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
    private Training systemUnderTest;

    @Before
    public void setUp() {
        // Arrange
        // Create an instance of Training to use in the tests
        systemUnderTest = new Training(1, "Cardio Kickboxing Class", new Date(), 101, 2, 60, 201);
    }

    @Test
    public void givenTrainingWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        int expectedId = 1;
        int actualId = systemUnderTest.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        int newId = 2;
        systemUnderTest.setid(newId);

        // Assert
        int actualId = systemUnderTest.getId();
        assertEquals(newId, actualId);
    }

    @Test
    public void givenTrainingWithTrainingName_whenGetTrainingName_thenShouldReturnTrainingName() {
        // Act and Assert
        String expectedTrainingName = "Cardio Kickboxing Class";
        String actualTrainingName = systemUnderTest.getTrainingName();
        assertEquals(expectedTrainingName, actualTrainingName);
    }

    @Test
    public void givenNewTrainingName_whenSetTrainingName_thenShouldUpdateTrainingName() {
        // Act
        String newTrainingName = "Strength and Conditioning Workshop";
        systemUnderTest.setTrainingName(newTrainingName);

        // Assert
        String actualTrainingName = systemUnderTest.getTrainingName();
        assertEquals(newTrainingName, actualTrainingName);
    }

    @Test
    public void givenNewTrainingDate_whenSetTrainingDate_thenShouldUpdateTrainingDate() throws ParseException {
        // Act
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newTrainingDate = sdf.parse("2023-11-15");
        systemUnderTest.setTrainingDate(newTrainingDate);

        // Assert
        Date actualTrainingDate = systemUnderTest.getTrainingDate();
        assertEquals(newTrainingDate, actualTrainingDate);
    }

    @Test
    public void givenTrainingWithTraineeId_whenGetTraineeId_thenShouldReturnTraineeId() {
        // Act and Assert
        int expectedTraineeId = 101;
        int actualTraineeId = systemUnderTest.getTraineeId();
        assertEquals(expectedTraineeId, actualTraineeId);
    }

    @Test
    public void givenNewTraineeId_whenSetTraineeId_thenShouldUpdateTraineeId() {
        // Act
        int newTraineeId = 102;
        systemUnderTest.setTraineeId(newTraineeId);

        // Assert
        int actualTraineeId = systemUnderTest.getTraineeId();
        assertEquals(newTraineeId, actualTraineeId);
    }

    @Test
    public void givenTrainingWithTrainingTypeId_whenGetTrainingTypeId_thenShouldReturnTrainingTypeId() {
        // Act and Assert
        int expectedTrainingTypeId = 2;
        int actualTrainingTypeId = systemUnderTest.getTrainingTypeId();
        assertEquals(expectedTrainingTypeId, actualTrainingTypeId);
    }

    @Test
    public void givenNewTrainingTypeId_whenSetTrainingTypeId_thenShouldUpdateTrainingTypeId() {
        // Act
        int newTrainingTypeId = 3;
        systemUnderTest.setTrainingTypeId(newTrainingTypeId);

        // Assert
        int actualTrainingTypeId = systemUnderTest.getTrainingTypeId();
        assertEquals(newTrainingTypeId, actualTrainingTypeId);
    }

    @Test
    public void givenTrainingWithTrainingDuration_whenGetTrainingDuration_thenShouldReturnTrainingDuration() {
        // Act and Assert
        int expectedTrainingDuration = 60;
        int actualTrainingDuration = systemUnderTest.getTrainingDuration();
        assertEquals(expectedTrainingDuration, actualTrainingDuration);
    }

    @Test
    public void givenNewTrainingDuration_whenSetTrainingDuration_thenShouldUpdateTrainingDuration() {
        // Act
        int newTrainingDuration = 90;
        systemUnderTest.setTrainingDuration(newTrainingDuration);

        // Assert
        int actualTrainingDuration = systemUnderTest.getTrainingDuration();
        assertEquals(newTrainingDuration, actualTrainingDuration);
    }

    @Test
    public void givenTrainingWithTrainerId_whenGetTrainerId_thenShouldReturnTrainerId() {
        // Act and Assert
        int expectedTrainerId = 201;
        int actualTrainerId = systemUnderTest.getTrainerId();
        assertEquals(expectedTrainerId, actualTrainerId);
    }

    @Test
    public void givenNewTrainerId_whenSetTrainerId_thenShouldUpdateTrainerId() {
        // Act
        int newTrainerId = 202;
        systemUnderTest.setTrainerId(newTrainerId);

        // Assert
        int actualTrainerId = systemUnderTest.getTrainerId();
        assertEquals(newTrainerId, actualTrainerId);
    }

    @Test
    public void givenTraining_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedString = "Training{id=1, trainingName='Cardio Kickboxing Class', trainingDate=" + systemUnderTest.getTrainingDate() + ", traineeId=101, trainingTypeId=2, trainingDuration=60, trainerId=201}";
        String actualString = systemUnderTest.toString();

        // Assert
        assertEquals(expectedString, actualString);
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainingObject() {
        // Act
        Training defaultTraining = new Training();

        // Assert
        assertNotNull(defaultTraining);
    }

}
