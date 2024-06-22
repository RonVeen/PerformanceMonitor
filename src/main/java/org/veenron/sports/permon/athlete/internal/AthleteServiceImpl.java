package org.veenron.sports.permon.athlete.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.veenron.sports.permon.athlete.Athlete;
import org.veenron.sports.permon.athlete.AthleteRepository;
import org.veenron.sports.permon.athlete.AthleteService;
import org.veenron.sports.permon.athlete.AthleteStatus;
import org.veenron.sports.permon.general.ShortUUID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AthleteServiceImpl implements AthleteService {

    final AthleteRepository repository;

    @Override

    public Optional<Athlete> createAthlete(String name, String email) {
        var now = LocalDateTime.now();
        return Optional.of(repository.save(new Athlete(0, ShortUUID.next(), name, email, AthleteStatus.Pending, now, now)));
    }

    @Override
    public Optional<Athlete> updateAthlete(Athlete original, Athlete updatedAthlete) {
        Athlete result = Athlete.builder()
                .id(original.id())
                .uid(original.uid())
                .name(updatedAthlete.name())
                .email(updatedAthlete.email())
                .status(original.status())
                .created(original.created())
                .updated(LocalDateTime.now())
                .build();
        return Optional.of(repository.save(result));

    }

    @Override
    public Optional<Athlete> getAthlete(String uid) {
        return repository.findByUid(uid);
    }

    @Override
    public boolean deleteAthlete(Athlete athlete) {
        repository.delete(athlete);
        return true;
    }

    @Override
    public List<Athlete> getAthletes() {
        var result = new ArrayList<Athlete>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Athlete blockAthlete(Athlete athlete) {
        return changeAthleteStatus(athlete, AthleteStatus.Blocked);
    }

    @Override
    public Athlete unblockAthlete(Athlete athlete) {
        return activateAthlete(athlete);
    }

    @Override
    public Athlete suspendAthlete(Athlete athlete) {
        return changeAthleteStatus(athlete, AthleteStatus.Suspended);
    }

    @Override
    public Athlete resumeAthlete(Athlete athlete) {
        return activateAthlete(athlete);
    }

    @Override
    public Athlete closeAthlete(Athlete athlete) {
        return changeAthleteStatus(athlete, AthleteStatus.Closed);
    }

    @Override
    public Athlete activateAthlete(Athlete athlete) {
        return changeAthleteStatus(athlete, AthleteStatus.Active);
    }

    private Athlete changeAthleteStatus(Athlete athlete, AthleteStatus status) {
        var updated = Athlete.builder()
                .id(athlete.id())
                .uid(athlete.uid())
                .name(athlete.name())
                .email(athlete.email())
                .status(status)
                .created(athlete.created())
                .updated(LocalDateTime.now())
                .build();
        return repository.save(updated);
    }
}
