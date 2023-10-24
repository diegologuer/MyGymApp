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
    public void testGetID() {
        assertEquals(1, trainer.getID());
    }

    @Test
    public void testSetID() {
        trainer.setID(2);
        assertEquals(2, trainer.getID());
    }

    @Test
    public void testGetUserId() {
        assertEquals(101, trainer.getUserId());
    }

    @Test
    public void testSetUserId() {
        trainer.setUserId(102);
        assertEquals(102, trainer.getUserId());
    }

    @Test
    public void testGetSpecialization() {
        assertEquals(2, trainer.getSpecialization());
    }

    @Test
    public void testSetSpecialization() {
        trainer.setSpecialization(3);
        assertEquals(3, trainer.getSpecialization());
    }

    @Test
    public void testToString() {
        String expectedString = "Trainer{id=1, userId=101, specialization='2'}";
        assertEquals(expectedString, trainer.toString());
    }

    @Test
    public void testDefaultConstructor() {
        Trainer defaultTrainer = new Trainer();
        assertNotNull(defaultTrainer);
    }
}

