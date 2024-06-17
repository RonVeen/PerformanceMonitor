package org.veenron.sports.permon.exercise;

import org.veenron.sports.permon.activity.Activity;
import org.veenron.sports.permon.athlete.Athlete;

import java.time.LocalDateTime;

public record Forecast(int id, String uid, Athlete athlete, Activity activity, String period, int count,
                       LocalDateTime created, LocalDateTime updated) {
}
