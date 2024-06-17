package org.veenron.sports.permon.athlete;


public enum AthleteStatus {
    Active("Active"),
    AwaitConfirmation("AwaitConfirmation"),
    Blocked("Blocked"),
    Suspended("Suspended"),
    Closed("Closed");

    private final String code;

    AthleteStatus(String code) {
        this.code = code;
    }
}