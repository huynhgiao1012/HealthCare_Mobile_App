package com.healthcareapp.healthcareappbackend.controller;

import com.healthcareapp.healthcareappbackend.service.SymptomPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/symptomPatient")
public class SymptomPatientController {
    @Autowired
    private SymptomPatientService symptomPatientService;
}
