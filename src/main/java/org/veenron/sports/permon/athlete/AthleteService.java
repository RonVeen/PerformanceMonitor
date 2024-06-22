package org.veenron.sports.permon.athlete;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AthleteService {

    Optional<Athlete> createAthlete(String name, String email);
    Optional<Athlete> updateAthlete(Athlete original, Athlete updatedAthlete);
    Optional<Athlete> getAthlete(String uid);
    boolean deleteAthlete(Athlete athlete);
    List<Athlete> getAthletes();

    Athlete blockAthlete(Athlete athlete);
    Athlete unblockAthlete(Athlete athlete);
    Athlete suspendAthlete(Athlete athlete);
    Athlete resumeAthlete(Athlete athlete);
    Athlete closeAthlete(Athlete athlete);
    Athlete activateAthlete(Athlete athlete);

}
