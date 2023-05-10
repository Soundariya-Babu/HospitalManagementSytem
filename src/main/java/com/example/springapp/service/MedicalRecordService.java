package com.example.springapp.service;

import java.util.List;
import java.util.Optional;

import com.example.springapp.model.MedicalRecord;

public interface MedicalRecordService {

	 public void saveMedicalRecord(MedicalRecord mc);
	 public void updateMedicalRecord( MedicalRecord mc);
	 public void deleteMedicalRecord(Long id);
	 public List<MedicalRecord> getAllMedicalRecord();
	 public Optional<MedicalRecord> getMedicalRecordById(Long id);
     public List<MedicalRecord> findByPatientId(Long patientId);

	 
	 
}
