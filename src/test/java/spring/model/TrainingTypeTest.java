package spring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.TrainingType;

public class TrainingTypeTest {
    private TrainingType trainingType;

    @Before
    public void setUp() {
        // Arrange
        // Create an instance of TrainingType to use in the tests
        trainingType = new TrainingType(1, "Cardio Workout");
    }

    @Test
    public void givenTrainingTypeWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        assertEquals(1, trainingType.getId());
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        trainingType.setId(2);

        // Assert
        assertEquals(2, trainingType.getId());
    }

    @Test
    public void givenTrainingTypeWithTrainingTypeName_whenGetTrainingTypeName_thenShouldReturnTrainingTypeName() {
        // Act and Assert
        assertEquals("Cardio Workout", trainingType.getTrainingTypeName());
    }

    @Test
    public void givenNewTrainingTypeName_whenSetTrainingTypeName_thenShouldUpdateTrainingTypeName() {
        // Act
        trainingType.setTrainingTypeName("Strength Training");

        // Assert
        assertEquals("Strength Training", trainingType.getTrainingTypeName());
    }

    @Test
    public void givenTrainingType_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedString = "TrainingType{id=1, trainingTypeName='Cardio Workout'}";

        // Assert
        assertEquals(expectedString, trainingType.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainingTypeObject() {
        // Act
        TrainingType defaultTrainingType = new TrainingType();

        // Assert
        assertNotNull(defaultTrainingType);
    }

}
