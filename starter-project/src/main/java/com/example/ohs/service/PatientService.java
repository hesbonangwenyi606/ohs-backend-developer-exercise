package com.example.ohs.service;

import com.example.ohs.domain.Patient;
import com.example.ohs.repository.PatientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class PatientService {
    private final PatientRepository repo;
    public PatientService(PatientRepository repo){this.repo = repo;}

    public Patient create(Patient p){
        if(p.getId()!=null) p.setId(null);
        return repo.save(p);
    }
    public Optional<Patient> findById(Long id){return repo.findById(id);}
    public Patient update(Long id, Patient update){
        return repo.findById(id).map(existing -> {
            existing.setIdentifier(update.getIdentifier());
            existing.setGivenName(update.getGivenName());
            existing.setFamilyName(update.getFamilyName());
            existing.setBirthDate(update.getBirthDate());
            existing.setGender(update.getGender());
            return repo.save(existing);
        }).orElseThrow(() -> new NoSuchElementException("Patient not found"));
    }
    public void delete(Long id){repo.deleteById(id);}

    public List<Patient> search(String family, String given, String identifier, LocalDate birthFrom, LocalDate birthTo){
        Specification<Patient> spec = Specification.where(null);
        if(family!=null) spec = spec.and((root, q, cb) -> cb.like(cb.lower(root.get("familyName")), "%" + family.toLowerCase() + "%"));
        if(given!=null) spec = spec.and((root, q, cb) -> cb.like(cb.lower(root.get("givenName")), "%" + given.toLowerCase() + "%"));
        if(identifier!=null) spec = spec.and((root, q, cb) -> cb.equal(root.get("identifier"), identifier));
        if(birthFrom!=null) spec = spec.and((root, q, cb) -> cb.greaterThanOrEqualTo(root.get("birthDate"), birthFrom));
        if(birthTo!=null) spec = spec.and((root, q, cb) -> cb.lessThanOrEqualTo(root.get("birthDate"), birthTo));
        return repo.findAll(spec);
    }
}
