package org.veenron.sports.permon.activity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.veenron.sports.permon.athlete.Athlete;

import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Optional<Activity> findByUid(String uid);
}
