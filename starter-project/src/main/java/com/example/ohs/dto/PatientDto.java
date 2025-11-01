package com.example.ohs.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record PatientDto(
    Long id,
    @NotBlank String identifier,
    @NotBlank String givenName,
    @NotBlank String familyName,
    @NotNull LocalDate birthDate,
    @NotBlank String gender
) {}
