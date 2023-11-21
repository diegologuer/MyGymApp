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
    void whenGettingTrainingTypeIdThenTrainingTypeIdIsReturned() {
        // When
        int actualTrainingTypeId = sut.getId();

        // Then
        assertEquals(1, actualTrainingTypeId);
    }

    @Test
    void whenSettingTrainingTypeIdThenTrainingTypeIdIsSet() {
        // Given
        int expectedTrainingTypeId = 123;

        // When
        sut.setId(expectedTrainingTypeId);

        // Then
        assertEquals(expectedTrainingTypeId, sut.getId());
    }

    @Test
    void whenGettingTrainingTypeNameThenTrainingTypeNameIsReturned() {
        // When
        String actualTrainingTypeName = sut.getTrainingTypeName();

        // Then
        assertEquals("Cardio", actualTrainingTypeName);
    }

    @Test
    void whenSettingTrainingTypeNameThenTrainingTypeNameIsSet() {
        // Given
        String expectedTrainingTypeName = "Strength";

        // When
        sut.setTrainingTypeName(expectedTrainingTypeName);

        // Then
        assertEquals(expectedTrainingTypeName, sut.getTrainingTypeName());
    }

    @Test
    void whenCallingTrainingTypeToStringThenCorrectStringIsReturned() {
        // Given
        String expectedToString = "TrainingType{id=1, trainingTypeName='Cardio'}";

        // When
        String actualToString = sut.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }
}