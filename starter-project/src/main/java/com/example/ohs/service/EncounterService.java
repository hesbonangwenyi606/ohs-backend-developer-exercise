package com.example.ohs.service;

import com.example.ohs.domain.Encounter;
import com.example.ohs.domain.Patient;
import com.example.ohs.repository.EncounterRepository;
import com.example.ohs.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EncounterService {
    private final EncounterRepository encounterRepository;
    private final PatientRepository patientRepository;
    public EncounterService(EncounterRepository encounterRepository, PatientRepository patientRepository){
        this.encounterRepository = encounterRepository;
        this.patientRepository = patientRepository;
    }

    public Encounter create(Encounter e, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("Patient not found"));
        e.setPatient(patient);
        return encounterRepository.save(e);
    }
    public List<Encounter> listForPatient(Long patientId){
        return encounterRepository.findByPatientId(patientId);
    }
}
