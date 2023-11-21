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
    void getId() {
        // Act
        int actualId = sut.getId();

        // Assert
        assertEquals(1, actualId);
    }

    @Test
    void setId() {
        // Arrange
        int expectedId = 123;

        // Act
        sut.setID(expectedId);

        // Assert
        assertEquals(expectedId, sut.getId());
    }

    @Test
    void getUserId() {
        // Act
        int actualUserId = sut.getUserId();

        // Assert
        assertEquals(5, actualUserId);
    }

    @Test
    void setUserId() {
        // Arrange
        int expectedUserId = 24;

        // Act
        sut.setUserId(expectedUserId);

        // Assert
        assertEquals(expectedUserId, sut.getUserId());
    }

    @Test
    void getSpecialization() {
        // Act
        int actualSpecialization = sut.getSpecialization();

        // Assert
        assertEquals(2, actualSpecialization);
    }

    @Test
    void setSpecialization() {
        // Arrange
        int expectedSpecialization = 3;

        // Act
        sut.setSpecialization(expectedSpecialization);

        // Assert
        assertEquals(expectedSpecialization, sut.getSpecialization());
    }

    @Test
    void testToString() {
        // Arrange
        String expectedToString = "Trainer{id=1, userId=5, specialization='2'}";

        // Act
        String actualToString = sut.toString();

        // Assert
        assertEquals(expectedToString, actualToString);
    }
}