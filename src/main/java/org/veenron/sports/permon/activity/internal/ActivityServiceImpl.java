package org.veenron.sports.permon.activity.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.veenron.sports.permon.activity.*;
import org.veenron.sports.permon.general.ShortUUID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ActivityServiceImpl implements ActivityService {

    final ActivityRepository repository;

    @Override
    public Optional<Activity> createActivity(String description, String remarks) {
        var now = LocalDateTime.now();
        return Optional.of(repository.save(new Activity(0, ShortUUID.next(), description, remarks, ActivityStatus.ACTIVE, now, now)));
    }

    @Override
    public Optional<Activity> updateActivity(Activity original, ActivityUpdate update) {
        Activity result = Activity.builder()
                .id(original.id())
                .uid(original.uid())
                .description(update.description())
                .remarks(update.remarks())
                .status(original.status())
                .created(original.created())
                .updated(LocalDateTime.now())
                .build();
        return Optional.of(repository.save(result));
    }

    @Override
    public Optional<Activity> getActivity(String uid) {
        return repository.findByUid(uid);
    }

    @Override
    public boolean deleteActivity(Activity activity) {
        repository.delete(activity);
        return true;
    }

    @Override
    public List<Activity> getActivities() {
        var result = new ArrayList<Activity>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Activity closeActivity(Activity activity) {
        return changeActivityStatus(activity, ActivityStatus.CLOSED);
    }

    @Override
    public Activity activateActivity(Activity activity) {
        return changeActivityStatus(activity, ActivityStatus.ACTIVE);
    }

    private Activity changeActivityStatus(Activity activity, ActivityStatus status) {
        var updated = Activity.builder()
                .id(activity.id())
                .uid(activity.uid())
                .description(activity.description())
                .remarks(activity.remarks())
                .status(status)
                .created(activity.created())
                .updated(LocalDateTime.now())
                .build();
        return repository.save(updated);

    }
}
