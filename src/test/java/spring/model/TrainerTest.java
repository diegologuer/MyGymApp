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
        // Create an instance of Trainer to use in the tests
        trainer = new Trainer(1, 101, 2);
    }

    @Test
    public void givenTrainerWithId_whenGetID_thenShouldReturnId() {
        assertEquals(1, trainer.getId());
    }

    @Test
    public void givenNewId_whenSetID_thenShouldUpdateId() {
        trainer.setID(2);
        assertEquals(2, trainer.getId());
    }

    @Test
    public void givenTrainerWithUserId_whenGetUserId_thenShouldReturnUserId() {
        assertEquals(101, trainer.getUserId());
    }

    @Test
    public void givenNewUserId_whenSetUserId_thenShouldUpdateUserId() {
        trainer.setUserId(102);
        assertEquals(102, trainer.getUserId());
    }

    @Test
    public void givenTrainerWithSpecialization_whenGetSpecialization_thenShouldReturnSpecialization() {
        assertEquals(2, trainer.getSpecialization());
    }

    @Test
    public void givenNewSpecialization_whenSetSpecialization_thenShouldUpdateSpecialization() {
        trainer.setSpecialization(3);
        assertEquals(3, trainer.getSpecialization());
    }

    @Test
    public void givenTrainer_whenToString_thenShouldReturnStringRepresentation() {
        String expectedString = "Trainer{id=1, userId=101, specialization='2'}";
        assertEquals(expectedString, trainer.toString());
    }

    @Test
    public void testDefaultConstructor_shouldCreateTrainerObject() {
        Trainer defaultTrainer = new Trainer();
        assertNotNull(defaultTrainer);
    }
}

