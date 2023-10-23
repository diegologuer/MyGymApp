package org.spring.service.trainer;

import org.spring.model.Trainer;


public interface TrainerServ {
    int createTrainer(String name, String lastname, int specialization);

    Trainer updateTrainer(int trainerId, int specialization);

    Trainer getTrainerById(int trainerId);

}
