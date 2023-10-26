package spring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.model.Trainer;

public class TrainerTest {
    private Trainer trainer;

    @Before
    public void setUp() {
        //Arrange
        // Create an instance of Trainer to use in the tests
        trainer = new Trainer(1, 101, 2);
    }

    @Test
    public void givenTrainerWithId_whenGetID_thenShouldReturnId() {
        //Act and assert
        assertEquals(1, trainer.getId());
    }

    @Test
    public void givenNewId_whenSetID_thenShouldUpdateId() {
        //Act
        trainer.setID(2);

        //Assert
        assertEquals(2, trainer.getId());
    }

    @Test
    public void givenTrainerWithUserId_whenGetUserId_thenShouldReturnUserId() {
        //Act and assert
        assertEquals(101, trainer.getUserId());
    }

    @Test
    public void givenNewUserId_whenSetUserId_thenShouldUpdateUserId() {
        //Act
        trainer.setUserId(102);

        //Assert
        assertEquals(102, trainer.getUserId());
    }

    @Test
    public void givenTrainerWithSpecialization_whenGetSpecialization_thenShouldReturnSpecialization() {
        //Act and assert
        assertEquals(2, trainer.getSpecialization());
    }

    @Test
    public void givenNewSpecialization_whenSetSpecialization_thenShouldUpdateSpecialization() {
        //Act
        trainer.setSpecialization(3);

        //Assert
        assertEquals(3, trainer.getSpecialization());
    }

    @Test
    public void givenTrainer_whenToString_thenShouldReturnStringRepresentation() {
        //Act
        String expectedString = "Trainer{id=1, userId=101, specialization='2'}";

        //Assert
        assertEquals(expectedString, trainer.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainerObject() {
        //Act
        Trainer defaultTrainer = new Trainer();

        //Assert
        assertNotNull(defaultTrainer);
    }
}

