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
        // Create an instance of TrainingType to use in the tests
        trainingType = new TrainingType(1, "Cardio Workout");
    }

    @Test
    public void testGetId() {
        assertEquals(1, trainingType.getId());
    }

    @Test
    public void testSetId() {
        trainingType.setId(2);
        assertEquals(2, trainingType.getId());
    }

    @Test
    public void testGetTrainingTypeName() {
        assertEquals("Cardio Workout", trainingType.getTrainingTypeName());
    }

    @Test
    public void testSetTrainingTypeName() {
        trainingType.setTrainingTypeName("Strength Training");
        assertEquals("Strength Training", trainingType.getTrainingTypeName());
    }

    @Test
    public void testToString() {
        String expectedString = "TrainingType{id=1, trainingTypeName='Cardio Workout'}";
        assertEquals(expectedString, trainingType.toString());
    }

    @Test
    public void testDefaultConstructor() {
        TrainingType defaultTrainingType = new TrainingType();
        assertNotNull(defaultTrainingType);
    }
}
