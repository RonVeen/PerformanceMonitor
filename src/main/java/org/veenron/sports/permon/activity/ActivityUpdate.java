package org.veenron.sports.permon.activity;

import jakarta.validation.constraints.NotBlank;

public record ActivityUpdate(@NotBlank String description, @NotBlank String remarks) {
}
