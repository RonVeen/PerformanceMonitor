package org.veenron.sports.permon.activity;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ActivityService {

    Optional<Activity> createActivity(String description, String remarks);
    Optional<Activity> updateActivity(Activity original, ActivityUpdate update);
    Optional<Activity> getActivity(String uid);
    boolean deleteActivity  (Activity activity);
    List<Activity> getActivities();

    Activity closeActivity(Activity activity);
    Activity activateActivity(Activity activity);
}
