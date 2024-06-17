package org.veenron.sports.permon.activity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {

    Activity createActivity(String description, String remarks);
    Activity updateActivity(String uid, Activity updatedActivity);
    Activity getActivity(String uid);
    boolean deleteActivity  (String uid);
    List<Activity> getActivities();

    boolean closeActivity(String uid);
    boolean openActivity(String uid);


}
