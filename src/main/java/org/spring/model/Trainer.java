package org.spring.model;

import java.util.Objects;

public class Trainer {
    private int id;
    private int userId;
    private int specialization;

    public Trainer() {

    }

    public Trainer(int id, int userId, int specialization) {
        this.id = id;
        this.userId = userId;
        this.specialization = specialization;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpecialization() {
        return specialization;
    }

    public void setSpecialization(int specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", userId=" + userId +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id && userId == trainer.userId && specialization == trainer.specialization;
    }


}
