package org.veenron.sports.permon.athlete.internal;

import org.veenron.sports.permon.athlete.Athlete;
import org.veenron.sports.permon.athlete.AthleteService;
import org.veenron.sports.permon.athlete.AthleteStatus;
import org.veenron.sports.permon.general.ShortUUID;

import java.time.LocalDateTime;
import java.util.List;

public class AthleteServiceImpl implements AthleteService {
    @Override
    public Athlete createAthlete(String name, String email) {
        var now = LocalDateTime.now();
        var athlete = new Athlete(0, ShortUUID.next(), name, email, AthleteStatus.Active, now, now);
        return null;
    }

    @Override
    public Athlete updateAthlete(String uid, Athlete updatedAtlete) {
        return null;
    }

    @Override
    public Athlete getAthlete(String uid) {
        return null;
    }

    @Override
    public boolean deleteAthlete(String uid) {
        return false;
    }

    @Override
    public List<Athlete> getAthletes() {
        return List.of();
    }

    @Override
    public boolean blockAthlete(String uid) {
        return false;
    }

    @Override
    public boolean unblockAthlete(String uid) {
        return false;
    }

    @Override
    public boolean suspendAthlete(String uid) {
        return false;
    }

    @Override
    public boolean resumeAthlete(String uid) {
        return false;
    }

    @Override
    public boolean closeAthlete(String uid) {
        return false;
    }

    @Override
    public boolean activateAthlete(String uid) {
        return false;
    }
}
