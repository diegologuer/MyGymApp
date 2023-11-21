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
    void whenGettingId_IdIsReturned() {
        //Act
        int actualId = sut.getId();

        //Assert
        assertEquals(1, actualId);
    }

    @Test
    void whenSettingId_IdIsSet() {
        //Arrange
        int expectedId = 123;

        //Act
        sut.setId(expectedId);

        //Assert
        assertEquals(expectedId, sut.getId());
    }

    @Test
    void getDateOfBirth() {
        //Arrange
        Date expectedDate = new Date(85, 5, 11);

        //Act
        Date actualDate = sut.getDateOfBirth();

        //Assert
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void setDateOfBirth() {
        //Arrange
        Date expectedDate = new Date(88, 3, 1);

        //Act
        sut.setDateOfBirth(expectedDate);

        //Assert
        assertEquals(expectedDate, sut.getDateOfBirth());

    }

    @Test
    void getAddress() {
        //Act
        String actualAddress = sut.getAddress();

        //Assert
        assertEquals("123 Main St", actualAddress);
    }

    @Test
    void setAddress() {
        //Arrange
        String expectedAddress = "45 Muscle Avenue";

        //Act
        sut.setAddress(expectedAddress);

        //Assert
        assertEquals(expectedAddress, sut.getAddress());
    }

    @Test
    void getUserId() {
        //Act
        int actualUserId = sut.getUserId();

        //Assert
        assertEquals(4, actualUserId);
    }

    @Test
    void setUserId() {
        //Arrange
        int expectedUserId = 24;

        //Act
        sut.setUserId(expectedUserId);

        //Assert
        assertEquals(expectedUserId, sut.getUserId());

    }

    @Test
    void testToString() {
        //Arrange
        String expectedToString = "Trainee{id=1, dateOfBirth=" + sut.getDateOfBirth() +
                ", address='123 Main St', userId=4}";

        //Act
        String actualToString = sut.toString();

        //Assert
        assertEquals(expectedToString, actualToString);
    }
}