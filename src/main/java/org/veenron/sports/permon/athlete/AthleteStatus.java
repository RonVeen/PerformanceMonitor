package org.veenron.sports.permon.athlete;


public enum AthleteStatus {
    Active("ACTV"),
    Pending("PNDG"),
    Blocked("BLCK"),
    Suspended("SPND"),
    Closed("ClSD");

    private final String code;

    AthleteStatus(String code) {
        this.code = code;
    }
}