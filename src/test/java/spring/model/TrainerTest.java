package spring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.Trainer;

public class TrainerTest {
    private Trainer systemUnderTest;

    @Before
    public void setUp() {
        // Arrange
        // Create an instance of Trainer to use in the tests
        systemUnderTest = new Trainer(1, 101, 2);
    }

    @Test
    public void givenTrainerWithId_whenGetId_thenShouldReturnId() {
        // Act and Assert
        int expectedId = 1;
        int actualId = systemUnderTest.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void givenNewId_whenSetId_thenShouldUpdateId() {
        // Act
        int newId = 2;
        systemUnderTest.setID(newId);

        // Assert
        int actualId = systemUnderTest.getId();
        assertEquals(newId, actualId);
    }

    @Test
    public void givenTrainerWithUserId_whenGetUserId_thenShouldReturnUserId() {
        // Act and Assert
        int expectedUserId = 101;
        int actualUserId = systemUnderTest.getUserId();
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void givenNewUserId_whenSetUserId_thenShouldUpdateUserId() {
        // Act
        int newUserId = 102;
        systemUnderTest.setUserId(newUserId);

        // Assert
        int actualUserId = systemUnderTest.getUserId();
        assertEquals(newUserId, actualUserId);
    }

    @Test
    public void givenTrainerWithSpecialization_whenGetSpecialization_thenShouldReturnSpecialization() {
        // Act and Assert
        int expectedSpecialization = 2;
        int actualSpecialization = systemUnderTest.getSpecialization();
        assertEquals(expectedSpecialization, actualSpecialization);
    }

    @Test
    public void givenNewSpecialization_whenSetSpecialization_thenShouldUpdateSpecialization() {
        // Act
        int newSpecialization = 3;
        systemUnderTest.setSpecialization(newSpecialization);

        // Assert
        int actualSpecialization = systemUnderTest.getSpecialization();
        assertEquals(newSpecialization, actualSpecialization);
    }

    @Test
    public void givenTrainer_whenToString_thenShouldReturnStringRepresentation() {
        // Act
        String expectedString = "Trainer{id=1, userId=101, specialization='2'}";
        String actualTrainer = systemUnderTest.toString();

        // Assert
        assertEquals(expectedString, actualTrainer);
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainerObject() {
        // Act
        Trainer defaultTrainer = new Trainer();

        // Assert
        assertNotNull(defaultTrainer);
    }
}

