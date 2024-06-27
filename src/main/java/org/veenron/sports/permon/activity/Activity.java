package org.veenron.sports.permon.activity;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Builder
public record Activity(@Id int id, String uid, String description, String remarks,
                       ActivityStatus status, LocalDateTime created, LocalDateTime updated) {
}
