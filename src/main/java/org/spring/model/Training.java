package org.spring.model;

import java.util.Date;

public class Training {
    private int id;
    private String trainingName;
    private Date trainingDate;
    private int traineeId;
    private int trainingTypeId;
    private int trainingDuration;
    private int trainerId;

    public Training(){

    }

    public Training(int id, String trainingName, Date trainingDate, int traineeId, int trainingTypeId, int trainingDuration, int trainerId) {
        this.id = id;
        this.trainingName = trainingName;
        this.trainingDate = trainingDate;
        this.traineeId = traineeId;
        this.trainingTypeId = trainingTypeId;
        this.trainingDuration = trainingDuration;
        this.trainerId = trainerId;
    }

    public int getid() {
        return id;
    }

    public void setid(int ID) {
        this.id = ID;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int trainee) {
        this.traineeId = trainee;
    }

    public int getTrainingTypeId() {
        return trainingTypeId;
    }

    public void setTrainingTypeId(int trainingTypeId) {
        this.trainingTypeId = trainingTypeId;
    }

    public int getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(int trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainer) {
        this.trainerId = trainer;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingName='" + trainingName + '\'' +
                ", trainingDate=" + trainingDate +
                ", traineeId=" + traineeId +
                ", trainingTypeId=" + trainingTypeId +
                ", trainingDuration=" + trainingDuration +
                ", trainerId=" + trainerId +
                '}';
    }
}
