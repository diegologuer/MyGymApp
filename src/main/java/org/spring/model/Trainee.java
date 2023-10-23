package org.spring.model;


import java.util.Date;

public class Trainee {
    private int id;
    private Date dateOfBirth;
    private String address;
    private int userId;

    public Trainee() {

    }

    public Trainee(int id, Date dateOfBirth, String address, int userId) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.userId = userId;
    }

    public int getID() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                '}';
    }
}
