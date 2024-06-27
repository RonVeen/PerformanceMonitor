package org.veenron.sports.permon.athlete.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.veenron.sports.permon.athlete.Athlete;
import org.veenron.sports.permon.athlete.AthleteRepository;
import org.veenron.sports.permon.athlete.AthleteStatus;

import java.time.LocalDateTime;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AthleteServiceImplTest {
    String name = "Arthur Dent";
    String email = "arthur.dent@gmail.com";
    Athlete athlete = new Athlete(42, "uid", name, email, AthleteStatus.PENDING, LocalDateTime.now(), LocalDateTime.now());


    @Mock
    AthleteRepository repository;

    @InjectMocks
    AthleteServiceImpl service;


    @Test
    void testCreateAthlete() {
        when(repository.save(any())).thenReturn(athlete);
        var result = service.createAthlete(name, email);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(name, result.get().name());
        assertEquals(email, result.get().email());
        assertEquals(AthleteStatus.PENDING, result.get().status());
        assertNotNull(result.get().created());
        assertNotNull(result.get().updated());
    }


    @Test
    void testBlockAthlete() {
        changeStatusAndAssert(service::blockAthlete, athlete, AthleteStatus.BLOCKED);
    }

    @Test
    void testUnblockAthlete() {
        changeStatusAndAssert(service::unblockAthlete, athlete, AthleteStatus.ACTIVE);
    }

    @Test
    void testSuspendAthlete() {
        changeStatusAndAssert(service::suspendAthlete, athlete, AthleteStatus.SUSPENDED);
    }

    @Test
    void testResumeAthlete() {
        changeStatusAndAssert(service::resumeAthlete, athlete, AthleteStatus.ACTIVE);
    }

    @Test
    void testCloseAthlete() {
        changeStatusAndAssert(service::closeAthlete, athlete, AthleteStatus.CLOSED);
    }

    @Test
    void testActivateAthlete() {
        changeStatusAndAssert(service::activateAthlete, athlete, AthleteStatus.ACTIVE);
    }




    void changeStatusAndAssert(Function<Athlete, Athlete> func, Athlete athlete, AthleteStatus status) {
        when(repository.save(any())).thenAnswer(a -> a.getArguments()[0]);
        var result = func.apply(athlete);
        assertEquals(status, result.status());
        assertTrue(result.updated().isAfter(athlete.updated()));
    }










}