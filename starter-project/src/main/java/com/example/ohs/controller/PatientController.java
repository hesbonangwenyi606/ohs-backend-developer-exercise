package com.example.ohs.controller;

import com.example.ohs.domain.Patient;
import com.example.ohs.dto.PatientDto;
import com.example.ohs.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Create a new patient
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto dto) {
        Patient patient = toEntity(dto);
        Patient saved = patientService.create(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long id) {
        return patientService.findById(id)
                .map(p -> ResponseEntity.ok(toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Update patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDto dto) {
        Patient patient = toEntity(dto);
        try {
            Patient updated = patientService.update(id, patient);
            return ResponseEntity.ok(toDto(updated));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Search patients
    @GetMapping
    public ResponseEntity<List<PatientDto>> searchPatients(
            @RequestParam(required = false) String family,
            @RequestParam(required = false) String given,
            @RequestParam(required = false) String identifier,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthTo
    ) {
        List<Patient> results = patientService.search(family, given, identifier, birthFrom, birthTo);
        List<PatientDto> dtos = results.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Helper: convert DTO → Entity
    private Patient toEntity(PatientDto dto) {
        Patient p = new Patient();
        p.setIdentifier(dto.identifier());
        p.setGivenName(dto.givenName());
        p.setFamilyName(dto.familyName());
        p.setBirthDate(dto.birthDate());
        p.setGender(dto.gender());
        return p;
    }

    // Helper: convert Entity → DTO
    private PatientDto toDto(Patient p) {
        return new PatientDto(
                p.getId(),
                p.getIdentifier(),
                p.getGivenName(),
                p.getFamilyName(),
                p.getBirthDate(),
                p.getGender()
        );
    }
}
