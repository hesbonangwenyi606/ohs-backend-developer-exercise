package com.example.ohs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "encounters", indexes = {
    @Index(name = "idx_encounter_patient", columnList = "patient_id"),
    @Index(name = "idx_encounter_start", columnList = "start_time")
})
public class Encounter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private OffsetDateTime start;

    @Column(name = "end_time")
    private OffsetDateTime end;

    @NotBlank @Column(name = "encounter_class", nullable = false)
    private String encounterClass;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public Patient getPatient(){return patient;}
    public void setPatient(Patient patient){this.patient = patient;}
    public OffsetDateTime getStart(){return start;}
    public void setStart(OffsetDateTime start){this.start = start;}
    public OffsetDateTime getEnd(){return end;}
    public void setEnd(OffsetDateTime end){this.end = end;}
    public String getEncounterClass(){return encounterClass;}
    public void setEncounterClass(String encounterClass){this.encounterClass = encounterClass;}
}
