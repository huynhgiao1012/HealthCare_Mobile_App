package com.healthcareapp.healthcareappbackend.service;

import com.healthcareapp.healthcareappbackend.converter.NewsMapper;
import com.healthcareapp.healthcareappbackend.converter.SymptomPatientMapper;
import com.healthcareapp.healthcareappbackend.repository.NewsRepository;
import com.healthcareapp.healthcareappbackend.repository.SymptomPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymptomPatientService {
    @Autowired
    private SymptomPatientRepository symptomPatientRepository;

    @Autowired
    private SymptomPatientMapper symptomPatientMapper;
}
