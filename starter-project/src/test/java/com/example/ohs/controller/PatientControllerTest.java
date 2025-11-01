package com.example.ohs.controller;

import com.example.ohs.domain.Patient;
import com.example.ohs.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(PatientController.class)
class PatientControllerTest {
    @Autowired MockMvc mvc;
    @MockBean PatientRepository repo;

    @Test
    void getNotFound() throws Exception {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        mvc.perform(get("/api/patients/1")).andExpect(status().isNotFound());
    }
}
