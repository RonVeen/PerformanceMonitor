package org.veenron.sports.permon.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
@RestController
@RequestMapping("activity")
public class ActivityResource {

    private final ActivityService activityService;


    @PostMapping
    ResponseEntity<Activity> createActivity(@RequestBody CreateActivity createActivity) {
        var result = activityService.createActivity(createActivity.description(), createActivity.remarks());
        return buildResponse(result.get(), HttpStatus.CREATED);
    }
    record CreateActivity(String description, String remarks) { }


    @GetMapping("/{uid}")
    ResponseEntity<Activity> getActivity(@PathVariable String uid) {
        var activity = activityService.getActivity(uid).orElseThrow(notFoundExceptionSupplier(uid));
        return buildResponse(activity, HttpStatus.OK);
    }

    @PutMapping("/{uid}")
    ResponseEntity<Activity> updateActivity(@PathVariable String uid, @RequestBody @Validated ActivityUpdate updated) {
        var original = activityService.getActivity(uid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, uid));
        var result = activityService.updateActivity(original, updated);
        return buildResponse(result.get(), HttpStatus.OK);
    }


    @DeleteMapping("/{uid}")
    ResponseEntity<Activity> deleteActivity(@PathVariable String uid) {
        var activity = activityService.getActivity(uid).orElseThrow(notFoundExceptionSupplier(uid));
        activityService.deleteActivity(activity);
        return buildResponse(activity, HttpStatus.OK);
    }


    @PutMapping("/{uid}/active")
    ResponseEntity<Activity> activateActivity(@PathVariable String uid) {
        return changeActivityStatus.apply(activityService.getActivity(uid), activityService::activateActivity);
    }

    @PutMapping("/{uid}/close")
    ResponseEntity<Activity> closeActivity(@PathVariable String uid) {
        return changeActivityStatus.apply(activityService.getActivity(uid), activityService::closeActivity);
    }


    ResponseEntity<Activity> buildResponse(Activity activity, HttpStatus successStatus) {
        return ResponseEntity.status(successStatus != null ? successStatus : HttpStatus.OK).body(activity);
   }

   BiFunction<Optional<Activity>, Function<Activity, Activity>, ResponseEntity<Activity>> changeActivityStatus = (activity, statusChangeFunc) -> {
       if (activity.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       var updated = statusChangeFunc.apply(activity.get());
       return buildResponse(updated, HttpStatus.OK);
   };


    Supplier<ResponseStatusException> notFoundExceptionSupplier(String reason) {
     return  () -> new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
   }

}