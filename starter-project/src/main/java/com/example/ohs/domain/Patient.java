package com.example.ohs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "patients", indexes = {
    @Index(name = "idx_patient_identifier", columnList = "identifier"),
    @Index(name = "idx_patient_family_given", columnList = "familyName,givenName")
})
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, unique = true)
    private String identifier;

    @NotBlank @Column(nullable = false)
    private String givenName;

    @NotBlank @Column(nullable = false)
    private String familyName;

    @Past @Column(nullable = false)
    private LocalDate birthDate;

    @NotBlank @Column(nullable = false)
    private String gender;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Encounter> encounters = new ArrayList<>();

    // getters and setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getIdentifier(){return identifier;}
    public void setIdentifier(String identifier){this.identifier = identifier;}
    public String getGivenName(){return givenName;}
    public void setGivenName(String givenName){this.givenName = givenName;}
    public String getFamilyName(){return familyName;}
    public void setFamilyName(String familyName){this.familyName = familyName;}
    public LocalDate getBirthDate(){return birthDate;}
    public void setBirthDate(LocalDate birthDate){this.birthDate = birthDate;}
    public String getGender(){return gender;}
    public void setGender(String gender){this.gender = gender;}
    public List<Encounter> getEncounters(){return encounters;}
    public void setEncounters(List<Encounter> encounters){this.encounters = encounters;}
}
