package com.example.springapp.service;

import java.util.List;
import java.util.Optional;


import com.example.springapp.model.Patient;

public interface PatientService {
	
	 public void savePatient(Patient patient);
	 public void updatePatient( Patient patient);
	 public void deletePatient(Long id);
	 public List<Patient> getAllPatient();
	 public Optional<Patient> getPatientById(Long id);

}
