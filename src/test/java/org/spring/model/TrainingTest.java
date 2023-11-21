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
    void whenGettingTrainingIdThenTrainingIdIsReturned() {
        // When
        int actualTrainingId = sut.getId();

        // Then
        assertEquals(1, actualTrainingId);
    }

    @Test
    void whenSettingTrainingIdThenTrainingIdIsSet() {
        // Given
        int expectedTrainingId = 123;

        // When
        sut.setid(expectedTrainingId);

        // Then
        assertEquals(expectedTrainingId, sut.getId());
    }

    @Test
    void whenGettingTrainingNameThenTrainingNameIsReturned() {
        // When
        String actualTrainingName = sut.getTrainingName();

        // Then
        assertEquals("Strength Training", actualTrainingName);
    }

    @Test
    void whenSettingTrainingNameThenTrainingNameIsSet() {
        // Given
        String expectedTrainingName = "Cardio Workout";

        // When
        sut.setTrainingName(expectedTrainingName);

        // Then
        assertEquals(expectedTrainingName, sut.getTrainingName());
    }

    @Test
    void whenGettingTrainingDateThenTrainingDateIsReturned() {
        // When
        Date actualTrainingDate = sut.getTrainingDate();

        // Then
        assertEquals(new Date(122, 10, 1), actualTrainingDate);
    }

    @Test
    void whenSettingTrainingDateThenTrainingDateIsSet() {
        // Given
        Date expectedTrainingDate = new Date(123, 0, 15);

        // When
        sut.setTrainingDate(expectedTrainingDate);

        // Then
        assertEquals(expectedTrainingDate, sut.getTrainingDate());
    }

    @Test
    void whenGettingTraineeIdThenTraineeIdIsReturned() {
        // When
        int actualTraineeId = sut.getTraineeId();

        // Then
        assertEquals(3, actualTraineeId);
    }

    @Test
    void whenSettingTraineeIdThenTraineeIdIsSet() {
        // Given
        int expectedTraineeId = 4;

        // When
        sut.setTraineeId(expectedTraineeId);

        // Then
        assertEquals(expectedTraineeId, sut.getTraineeId());
    }

    @Test
    void whenGettingTrainingTypeIdThenTrainingTypeIdIsReturned() {
        // When
        int actualTrainingTypeId = sut.getTrainingTypeId();

        // Then
        assertEquals(2, actualTrainingTypeId);
    }

    @Test
    void whenSettingTrainingTypeIdThenTrainingTypeIdIsSet() {
        // Given
        int expectedTrainingTypeId = 3;

        // When
        sut.setTrainingTypeId(expectedTrainingTypeId);

        // Then
        assertEquals(expectedTrainingTypeId, sut.getTrainingTypeId());
    }

    @Test
    void whenGettingTrainingDurationThenTrainingDurationIsReturned() {
        // When
        int actualTrainingDuration = sut.getTrainingDuration();

        // Then
        assertEquals(10, actualTrainingDuration);
    }

    @Test
    void whenSettingTrainingDurationThenTrainingDurationIsSet() {
        // Given
        int expectedTrainingDuration = 15;

        // When
        sut.setTrainingDuration(expectedTrainingDuration);

        // Then
        assertEquals(expectedTrainingDuration, sut.getTrainingDuration());
    }

    @Test
    void whenGettingTrainerIdThenTrainerIdIsReturned() {
        // When
        int actualTrainerId = sut.getTrainerId();

        // Then
        assertEquals(5, actualTrainerId);
    }

    @Test
    void whenSettingTrainerIdThenTrainerIdIsSet() {
        // Given
        int expectedTrainerId = 8;

        // When
        sut.setTrainerId(expectedTrainerId);

        // Then
        assertEquals(expectedTrainerId, sut.getTrainerId());
    }

    @Test
    void whenCallingTrainingToStringThenCorrectStringIsReturned() {
        // Given
        String expectedToString = "Training{id=1, trainingName='Strength Training', trainingDate=" + sut.getTrainingDate() +
                ", traineeId=3, trainingTypeId=2, trainingDuration=10, trainerId=5}";

        // When
        String actualToString = sut.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }
}