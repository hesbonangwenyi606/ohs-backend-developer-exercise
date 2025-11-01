package com.example.ohs.service;

import com.example.ohs.domain.Patient;
import com.example.ohs.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class PatientServiceTest {

    @Test
    void createAndFind() {
        PatientRepository repo = Mockito.mock(PatientRepository.class);
        PatientService svc = new PatientService(repo);

        Patient p = new Patient();
        p.setIdentifier("ID123");
        p.setGivenName("Test");
        p.setFamilyName("User");
        p.setBirthDate(LocalDate.of(1990,1,1));
        p.setGender("M");

        Mockito.when(repo.save(Mockito.any())).thenAnswer(i -> { Patient x = i.getArgument(0); x.setId(1L); return x;});
        Patient saved = svc.create(p);
        assertThat(saved.getId()).isEqualTo(1L);
    }
}
