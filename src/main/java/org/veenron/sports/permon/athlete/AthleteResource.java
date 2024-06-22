package org.veenron.sports.permon.athlete;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
@RestController
@RequestMapping("athlete")
public class AthleteResource {

    private final AthleteService athleteService;

    @PostMapping
    ResponseEntity<Athlete> createAthlete(@RequestBody CreateAthlete createAthlete) {
        var result = athleteService.createAthlete(createAthlete.name(), createAthlete.email());
        return buildResponse(result, HttpStatus.CREATED, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    record CreateAthlete(String name, String email) { }


    @GetMapping("/{uid}")
    ResponseEntity<Athlete> getAthlete(@PathVariable String uid) {
        var athlete = athleteService.getAthlete(uid);
        return buildResponse(athlete, HttpStatus.OK, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{uid}")
    ResponseEntity<Athlete> updateAthlete(@PathVariable String uid, @RequestBody UpdateAthlete updated) {
        var original = athleteService.getAthlete(uid);
        if (original.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var result = athleteService.updateAthlete(
                original.get(),
                new Athlete(0, null, updated.name() ,updated.email(), null, null, null)
        );
        return buildResponse(result, HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    record UpdateAthlete(String name, String email) { }


    @DeleteMapping("/{uid}")
    ResponseEntity<Athlete> deleteAthlete(@PathVariable String uid) {
        var athlete = athleteService.getAthlete(uid);
        if (athlete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        athleteService.deleteAthlete(athlete.get());
        return buildResponse(athlete, HttpStatus.OK, null);
    }


    @PutMapping("/{uid}/activate")
    ResponseEntity<Athlete> activateAthlete(@PathVariable String uid) {
        return changeAthleteStatus.apply(athleteService.getAthlete(uid), athleteService::activateAthlete);
    }

    @PutMapping("/{uid}/block")
    ResponseEntity<Athlete> blockAthlete(@PathVariable String uid) {
        return changeAthleteStatus.apply(athleteService.getAthlete(uid), athleteService::blockAthlete);
    }

    @PutMapping("/{uid}/unblock")
    ResponseEntity<Athlete> unblockAthlete(@PathVariable String uid) {
        return changeAthleteStatus.apply(athleteService.getAthlete(uid), athleteService::unblockAthlete);
    }

    @PutMapping("/{uid}/suspend")
    ResponseEntity<Athlete> suspendAthlete(@PathVariable String uid) {
        return changeAthleteStatus.apply(athleteService.getAthlete(uid), athleteService::suspendAthlete);
    }

    @PutMapping("/{uid}/resume")
    ResponseEntity<Athlete> resumeAthlete(@PathVariable String uid) {
        return changeAthleteStatus.apply(athleteService.getAthlete(uid), athleteService::resumeAthlete);
    }

    private ResponseEntity<Athlete> buildResponse(Optional<Athlete> value, HttpStatus successStatus, HttpStatus failureStatus) {
        return value.map(athlete -> ResponseEntity.status(successStatus != null ? successStatus : HttpStatus.OK).body(athlete))
                    .orElseGet(() -> ResponseEntity.status(failureStatus != null ? failureStatus : HttpStatus.NOT_FOUND).build());
   }

   private BiFunction<Optional<Athlete>, Function<Athlete, Athlete>, ResponseEntity<Athlete>> changeAthleteStatus = (athlete, statusChangeFunc) -> {
       if (athlete.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       var updated = statusChangeFunc.apply(athlete.get());
       return buildResponse(Optional.of(updated), HttpStatus.OK, null);
   };
}