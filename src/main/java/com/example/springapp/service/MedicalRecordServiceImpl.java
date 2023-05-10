package com.example.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.MedicalRecord;
import com.example.springapp.repository.MedicalRecordRepository;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepo;

	@Override
	public void saveMedicalRecord(MedicalRecord mc) {
		mc.setDate(LocalDate.now());
		medicalRecordRepo.save(mc);
	}

	@Override
	public void updateMedicalRecord(MedicalRecord mc) {
		if(medicalRecordRepo.existsById(mc.getId()))
		{
			medicalRecordRepo.save(mc);
	    }
	}

	@Override
	public void deleteMedicalRecord(Long id) {
		medicalRecordRepo.deleteById(id);
	}

	@Override
	public List<MedicalRecord> getAllMedicalRecord() {
		List<MedicalRecord> medicalRecords=medicalRecordRepo.findAll();		
		return medicalRecords;
	}

	@Override
	public Optional<MedicalRecord> getMedicalRecordById(Long id) {
		Optional<MedicalRecord> mc=medicalRecordRepo.findById(id);
		return mc;
	}

	@Override
	public List<MedicalRecord> findByPatientId(Long patientId) {
		List<MedicalRecord> medicalRecords=medicalRecordRepo.findByPatientId(patientId);		
		return medicalRecords;
	}

}
