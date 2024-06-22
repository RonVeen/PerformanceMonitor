package org.veenron.sports.permon.athlete;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AthleteRepository extends CrudRepository<Athlete, Long> {
    Optional<Athlete> findByUid(String uid);
}
