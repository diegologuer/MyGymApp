package org.spring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TraineeTest {

    private Trainee sut;

    @BeforeEach
    public void setUp(){
        Date dateOfBirth = new Date(85, 5, 11);
        sut = new Trainee(1, dateOfBirth, "123 Main St",4);
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
        sut.setId(expectedId);

        // Then
        assertEquals(expectedId, sut.getId());
    }

    @Test
    void whenGettingDateOfBirthThenDateOfBirthIsReturned() {
        // Given
        Date expectedDate = new Date(85, 5, 11);

        // When
        Date actualDate = sut.getDateOfBirth();

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void whenSettingDateOfBirthThenDateOfBirthIsSet() {
        // Given
        Date expectedDate = new Date(88, 3, 1);

        // When
        sut.setDateOfBirth(expectedDate);

        // Then
        assertEquals(expectedDate, sut.getDateOfBirth());
    }

    @Test
    void whenGettingAddressThenAddressIsReturned() {
        // When
        String actualAddress = sut.getAddress();

        // Then
        assertEquals("123 Main St", actualAddress);
    }

    @Test
    void whenSettingAddressThenAddressIsSet() {
        // Given
        String expectedAddress = "45 Muscle Avenue";

        // When
        sut.setAddress(expectedAddress);

        // Then
        assertEquals(expectedAddress, sut.getAddress());
    }

    @Test
    void whenGettingUserIdThenUserIdIsReturned() {
        // When
        int actualUserId = sut.getUserId();

        // Then
        assertEquals(4, actualUserId);
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
    void whenCallingToStringThenCorrectStringIsReturned() {
        // Given
        String expectedToString = "Trainee{id=1, dateOfBirth=" + sut.getDateOfBirth() +
                ", address='123 Main St', userId=4}";

        // When
        String actualToString = sut.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }
}