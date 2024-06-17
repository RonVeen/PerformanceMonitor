package org.veenron.sports.permon.activity;

import java.time.LocalDateTime;

enum Status {
    Active("Active"),
    Closed("Closed");

    private final String status;
    Status(String status) {
        this.status = status;
    }

}
public record Activity(int id, String uid, String description, String remarks,
                       Status status, LocalDateTime created, LocalDateTime closed) {
}
