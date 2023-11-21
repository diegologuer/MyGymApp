package org.spring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {

    private Trainer sut;

    @BeforeEach
    public void setUp() {
        sut = new Trainer(1, 5, 2);
    }


    @Test
    void whenGettingIdThenIdIsReturned() {
        // When
        int actualId = sut.getId();

        // Then
        assertEquals(1, actualId);
    }

    @Test
    void whenSettingIdThenIdIsSet() {
        // Given
        int expectedId = 123;

        // When
        sut.setID(expectedId);

        // Then
        assertEquals(expectedId, sut.getId());
    }

    @Test
    void whenGettingUserIdThenUserIdIsReturned() {
        // When
        int actualUserId = sut.getUserId();

        // Then
        assertEquals(5, actualUserId);
    }

    @Test
    void whenSettingUserIdThenUserIdIsSet() {
        // Given
        int expectedUserId = 24;

        // When
        sut.setUserId(expectedUserId);

        // Then
        assertEquals(expectedUserId, sut.getUserId());
    }

    @Test
    void whenGettingSpecializationThenSpecializationIsReturned() {
        // When
        int actualSpecialization = sut.getSpecialization();

        // Then
        assertEquals(2, actualSpecialization);
    }

    @Test
    void whenSettingSpecializationThenSpecializationIsSet() {
        // Given
        int expectedSpecialization = 3;

        // When
        sut.setSpecialization(expectedSpecialization);

        // Then
        assertEquals(expectedSpecialization, sut.getSpecialization());
    }

    @Test
    void whenCallingToStringThenCorrectStringIsReturned() {
        // Given
        String expectedToString = "Trainer{id=1, userId=5, specialization='2'}";

        // When
        String actualToString = sut.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }
}