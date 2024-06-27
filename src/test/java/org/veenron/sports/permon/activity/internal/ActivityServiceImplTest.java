package org.veenron.sports.permon.activity.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.veenron.sports.permon.activity.Activity;
import org.veenron.sports.permon.activity.ActivityRepository;
import org.veenron.sports.permon.activity.ActivityStatus;

import java.time.LocalDateTime;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    final String description = "Spinning";
    final String remarks = "Indoor";
    Activity activity = new Activity(42, "uid", description, remarks, ActivityStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());

    @Mock
    ActivityRepository repository;

    @InjectMocks
    ActivityServiceImpl service;

    @Test
    void createActivity() {
        when(repository.save(any())).thenReturn(activity);
        var result = service.createActivity(description, remarks);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(description, result.get().description());
        assertEquals(remarks, result.get().remarks());
        assertEquals(ActivityStatus.ACTIVE, result.get().status());
        assertNotNull(result.get().created());
        assertNotNull(result.get().updated());
    }

    @Test
    void closeActivity() {
        changeStatusAndAssert(service::closeActivity, activity, ActivityStatus.CLOSED);
    }

    @Test
    void activateActivity() {
        changeStatusAndAssert(service::activateActivity, activity, ActivityStatus.ACTIVE);
    }


    void changeStatusAndAssert(Function<Activity, Activity> func, Activity activity, ActivityStatus status) {
        when(repository.save(any())).thenAnswer(a -> a.getArguments()[0]);
        var result = func.apply(activity);
        assertEquals(status, result.status());
        assertTrue(result.updated().isAfter(activity.updated()));
    }
}