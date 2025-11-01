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
    private final PatientService service;
    public PatientController(PatientService service){this.service = service;}

    @PostMapping
    public ResponseEntity<PatientDto> create(@Valid @RequestBody PatientDto dto){
        Patient p = toEntity(dto);
        Patient saved = service.create(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> get(@PathVariable Long id){
        return service.findById(id).map(p -> ResponseEntity.ok(toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable Long id, @Valid @RequestBody PatientDto dto){
        Patient updated = toEntity(dto);
        try {
            Patient saved = service.update(id, updated);
            return ResponseEntity.ok(toDto(saved));
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> search(
            @RequestParam(required = false) String family,
            @RequestParam(required = false) String given,
            @RequestParam(required = false) String identifier,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthTo
    ){
        List<Patient> results = service.search(family, given, identifier, birthFrom, birthTo);
        List<PatientDto> dtos = results.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private Patient toEntity(PatientDto dto){
        Patient p = new Patient();
        p.setIdentifier(dto.identifier());
        p.setGivenName(dto.givenName());
        p.setFamilyName(dto.familyName());
        p.setBirthDate(dto.birthDate());
        p.setGender(dto.gender());
        return p;
    }
    private PatientDto toDto(Patient p){
        return new PatientDto(p.getId(), p.getIdentifier(), p.getGivenName(), p.getFamilyName(), p.getBirthDate(), p.getGender());
    }
}
