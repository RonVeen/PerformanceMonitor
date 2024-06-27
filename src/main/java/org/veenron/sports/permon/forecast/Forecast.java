package org.veenron.sports.permon.forecast;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.veenron.sports.permon.activity.Activity;
import org.veenron.sports.permon.athlete.Athlete;

import java.time.LocalDateTime;

public record Forecast(int id, String uid, AggregateReference<Athlete, Integer> athlete, AggregateReference<Activity, Integer> activity, String period, int count,
                       LocalDateTime created, LocalDateTime updated) {

}
