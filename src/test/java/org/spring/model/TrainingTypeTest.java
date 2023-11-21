package org.spring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTypeTest {

    private TrainingType sut;

    @BeforeEach
    public void setUp() {
        sut = new TrainingType(1, "Cardio");
    }

    @Test
    void getTrainingTypeId() {
        // Act
        int actualTrainingTypeId = sut.getId();

        // Assert
        assertEquals(1, actualTrainingTypeId);
    }

    @Test
    void setTrainingTypeId() {
        // Arrange
        int expectedTrainingTypeId = 123;

        // Act
        sut.setId(expectedTrainingTypeId);

        // Assert
        assertEquals(expectedTrainingTypeId, sut.getId());
    }

    @Test
    void getTrainingTypeName() {
        // Act
        String actualTrainingTypeName = sut.getTrainingTypeName();

        // Assert
        assertEquals("Cardio", actualTrainingTypeName);
    }

    @Test
    void setTrainingTypeName() {
        // Arrange
        String expectedTrainingTypeName = "Strength";

        // Act
        sut.setTrainingTypeName(expectedTrainingTypeName);

        // Assert
        assertEquals(expectedTrainingTypeName, sut.getTrainingTypeName());
    }

    @Test
    void testTrainingTypeToString() {
        // Arrange
        String expectedToString = "TrainingType{id=1, trainingTypeName='Cardio'}";

        // Act
        String actualToString = sut.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
}