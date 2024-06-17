package org.veenron.sports.permon.athlete;

import java.time.LocalDateTime;

public record Athlete(int id, String uid, String name, String email,
                      AthleteStatus status, LocalDateTime created, LocalDateTime updated) {
}




