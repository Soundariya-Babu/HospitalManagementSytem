package com.example.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Patient;
import com.example.springapp.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepo;

	@Override
	public void savePatient(Patient patient) {
		patientRepo.save(patient);
	}

	@Override
	public void updatePatient(Patient patient) {
		if(patientRepo.existsById(patient.getId()))
		{
			patientRepo.save(patient);
	    }	}

	@Override
	public void deletePatient(Long id) {
		patientRepo.deleteById(id);
	}

	@Override
	public List<Patient> getAllPatient() {
		List<Patient> patientList= patientRepo.findAll();
		return patientList;
	}

	@Override
	public Optional<Patient> getPatientById(Long id) {
		Optional<Patient> patient = patientRepo.findById(id);
		System.out.println(patient);
		return patient;
	}

}
