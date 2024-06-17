package org.veenron.sports.permon.exercise;

import org.veenron.sports.permon.activity.Activity;
import org.veenron.sports.permon.athlete.Athlete;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Training(int id, String uuid, Athlete athlete, LocalDate when, String period,
                       Activity activity, String remarks,
                       LocalDateTime created, LocalDateTime updated) { }
