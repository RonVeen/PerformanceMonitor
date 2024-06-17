package org.veenron.sports.permon.athlete;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AthleteService {

    Athlete createAthlete(String name, String email);
    Athlete updateAthlete(String uid, Athlete updatedAtlete);
    Athlete getAthlete(String uid);
    boolean deleteAthlete(String uid);
    List<Athlete> getAthletes();

    boolean blockAthlete(String uid);
    boolean unblockAthlete(String uid);
    boolean suspendAthlete(String uid);
    boolean resumeAthlete(String uid);
    boolean closeAthlete(String uid);
    boolean activateAthlete(String uid);

}
