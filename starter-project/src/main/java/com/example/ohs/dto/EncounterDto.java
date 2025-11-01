package com.example.ohs.dto;

import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;

public record EncounterDto(
    Long id,
    Long patientId,
    @NotNull OffsetDateTime start,
    OffsetDateTime end,
    @NotBlank String encounterClass
) {}
