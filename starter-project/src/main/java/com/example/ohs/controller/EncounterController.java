package com.example.ohs.controller;

import com.example.ohs.domain.Encounter;
import com.example.ohs.dto.EncounterDto;
import com.example.ohs.service.EncounterService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EncounterController {
    private final EncounterService service;
    public EncounterController(EncounterService service){this.service = service;}

    @PostMapping("/patients/{patientId}/encounters")
    public ResponseEntity<EncounterDto> create(@PathVariable Long patientId, @Valid @RequestBody EncounterDto dto){
        Encounter e = toEntity(dto);
        var saved = service.create(e, patientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @GetMapping("/patients/{patientId}/encounters")
    public ResponseEntity<List<EncounterDto>> list(@PathVariable Long patientId){
        List<Encounter> list = service.listForPatient(patientId);
        List<EncounterDto> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private Encounter toEntity(EncounterDto dto){
        Encounter e = new Encounter();
        e.setStart(dto.start());
        e.setEnd(dto.end());
        e.setEncounterClass(dto.encounterClass());
        return e;
    }
    private EncounterDto toDto(Encounter e){
        return new EncounterDto(e.getId(), e.getPatient().getId(), e.getStart(), e.getEnd(), e.getEncounterClass());
    }
}
