package org.veenron.sports.permon.forecast;

import org.springframework.stereotype.Service;
import org.veenron.sports.permon.activity.Activity;
import org.veenron.sports.permon.athlete.Athlete;

import java.util.List;

@Service
public interface ForecastService {

    Forecast createForecast(Athlete athlete, Activity activity, String period, int count);
    Forecast updateForecast(String forecastUid, Forecast updatedForecast);
    Forecast getForecast(String forecastUid);
    Forecast deleteForecast(String forecastUid);

    List<Forecast> getForecastForAthleteAndPeriodd(Athlete athlete, String period);
    List<Forecast> duplicateForecastOfPeriod(Athlete athlete, String period, String newPeriod);
}
