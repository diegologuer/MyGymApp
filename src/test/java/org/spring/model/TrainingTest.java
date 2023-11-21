package org.spring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTest {

    private Training sut;

    @BeforeEach
    public void setUp() {
        Date trainingDate = new Date(122, 10, 1);
        sut = new Training(1, "Strength Training", trainingDate, 3, 2, 10, 5);
    }

    @Test
    void getTrainingId() {
        // Act
        int actualTrainingId = sut.getId();

        // Assert
        assertEquals(1, actualTrainingId);
    }

    @Test
    void setTrainingId() {
        // Arrange
        int expectedTrainingId = 123;

        // Act
        sut.setid(expectedTrainingId);

        // Assert
        assertEquals(expectedTrainingId, sut.getId());
    }

    @Test
    void getTrainingName() {
        // Act
        String actualTrainingName = sut.getTrainingName();

        // Assert
        assertEquals("Strength Training", actualTrainingName);
    }

    @Test
    void setTrainingName() {
        // Arrange
        String expectedTrainingName = "Cardio Workout";

        // Act
        sut.setTrainingName(expectedTrainingName);

        // Assert
        assertEquals(expectedTrainingName, sut.getTrainingName());
    }

    @Test
    void getTrainingDate() {
        // Act
        Date actualTrainingDate = sut.getTrainingDate();

        // Assert
        assertEquals(new Date(122, 10, 1), actualTrainingDate);
    }

    @Test
    void setTrainingDate() {
        // Arrange
        Date expectedTrainingDate = new Date(123, 0, 15);

        // Act
        sut.setTrainingDate(expectedTrainingDate);

        // Assert
        assertEquals(expectedTrainingDate, sut.getTrainingDate());
    }

    @Test
    void getTraineeId() {
        // Act
        int actualTraineeId = sut.getTraineeId();

        // Assert
        assertEquals(3, actualTraineeId);
    }

    @Test
    void setTraineeId() {
        // Arrange
        int expectedTraineeId = 4;

        // Act
        sut.setTraineeId(expectedTraineeId);

        // Assert
        assertEquals(expectedTraineeId, sut.getTraineeId());
    }

    @Test
    void getTrainingTypeId() {
        // Act
        int actualTrainingTypeId = sut.getTrainingTypeId();

        // Assert
        assertEquals(2, actualTrainingTypeId);
    }

    @Test
    void setTrainingTypeId() {
        // Arrange
        int expectedTrainingTypeId = 3;

        // Act
        sut.setTrainingTypeId(expectedTrainingTypeId);

        // Assert
        assertEquals(expectedTrainingTypeId, sut.getTrainingTypeId());
    }

    @Test
    void getTrainingDuration() {
        // Act
        int actualTrainingDuration = sut.getTrainingDuration();

        // Assert
        assertEquals(10, actualTrainingDuration);
    }

    @Test
    void setTrainingDuration() {
        // Arrange
        int expectedTrainingDuration = 15;

        // Act
        sut.setTrainingDuration(expectedTrainingDuration);

        // Assert
        assertEquals(expectedTrainingDuration, sut.getTrainingDuration());
    }

    @Test
    void getTrainerId() {
        // Act
        int actualTrainerId = sut.getTrainerId();

        // Assert
        assertEquals(5, actualTrainerId);
    }

    @Test
    void setTrainerId() {
        // Arrange
        int expectedTrainerId = 8;

        // Act
        sut.setTrainerId(expectedTrainerId);

        // Assert
        assertEquals(expectedTrainerId, sut.getTrainerId());
    }

    @Test
    void testTrainingToString() {
        // Arrange
        String expectedToString = "Training{id=1, trainingName='Strength Training', trainingDate=" + sut.getTrainingDate() +
                ", traineeId=3, trainingTypeId=2, trainingDuration=10, trainerId=5}";

        // Act
        String actualToString = sut.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
}