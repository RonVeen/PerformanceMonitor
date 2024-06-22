package org.veenron.sports.permon.athlete;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("athlete")
@Builder
public record Athlete(@Id int id, String uid, String name, String email,
                      AthleteStatus status, LocalDateTime created, LocalDateTime updated) {
}




