package spring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.TrainingType;

public class TrainingTypeTest {
    private TrainingType systemUnderTest;

    @Before
    public void setUp() {
        // Arrange
        // Create an instance of TrainingType to use in the tests
        systemUnderTest = new TrainingType(1, "Cardio Workout");
    }

    @Test
    public void givenTrainingTypeWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        int expectedId = 1;
        int actualId = systemUnderTest.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        int newId = 2;
        systemUnderTest.setId(newId);

        // Assert
        int actualId = systemUnderTest.getId();
        assertEquals(newId, actualId);
    }

    @Test
    public void givenTrainingTypeWithTrainingTypeName_whenGetTrainingTypeName_thenShouldReturnTrainingTypeName() {
        // Act and Assert
        String expectedTrainingTypeName = "Cardio Workout";
        String actualTrainingTypeName = systemUnderTest.getTrainingTypeName();
        assertEquals(expectedTrainingTypeName, actualTrainingTypeName);
    }

    @Test
    public void givenNewTrainingTypeName_whenSetTrainingTypeName_thenShouldUpdateTrainingTypeName() {
        // Act
        String newTrainingTypeName = "Strength Training";
        systemUnderTest.setTrainingTypeName(newTrainingTypeName);

        // Assert
        String actualTrainingTypeName = systemUnderTest.getTrainingTypeName();
        assertEquals(newTrainingTypeName, actualTrainingTypeName);
    }

    @Test
    public void givenTrainingType_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedTrainingType = "TrainingType{id=1, trainingTypeName='Cardio Workout'}";
        String actualTrainingType = systemUnderTest.toString();

        // Assert
        assertEquals(expectedTrainingType, actualTrainingType);
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainingTypeObject() {
        // Act
        TrainingType defaultTrainingType = new TrainingType();

        // Assert
        assertNotNull(defaultTrainingType);
    }

}
