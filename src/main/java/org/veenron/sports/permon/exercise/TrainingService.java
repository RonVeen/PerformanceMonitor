package org.veenron.sports.permon.exercise;

import org.veenron.sports.permon.activity.Activity;
import org.veenron.sports.permon.athlete.Athlete;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {

    Training addTraining(String athleteUid, Activity activity, LocalDate when, String remarks);
    Training updateTraining(String uid, Training updatedTraining);
    Training getTraining(String uid);
    Training deleteTraining(String uid);

    List<Training> getTrainingForAthleteInPeriod(Athlete athlete, String period);
    List<Training> getTrainingForAthleteAndActivityInPeriod(Athlete athlete, Activity activity, String period);

}
