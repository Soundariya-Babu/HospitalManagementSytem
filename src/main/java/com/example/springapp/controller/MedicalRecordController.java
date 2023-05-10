package com.example.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.MedicalRecord;
import com.example.springapp.service.MedicalRecordService;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@PostMapping
	public ResponseEntity<MedicalRecord> save(@RequestBody MedicalRecord medicalRecord){
			
		medicalRecordService.saveMedicalRecord(medicalRecord);
		return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<MedicalRecord> update(@RequestBody MedicalRecord medicalRecord){
			
		medicalRecordService.updateMedicalRecord(medicalRecord);
		return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam Long id){
			
		medicalRecordService.deleteMedicalRecord(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<MedicalRecord>> getAll(){
			
		List <MedicalRecord> medicalRecords=medicalRecordService.getAllMedicalRecord();
		return new ResponseEntity<> (medicalRecords, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Optional<MedicalRecord>> getById(@RequestParam Long id){
			
		Optional<MedicalRecord> medicalRecord=medicalRecordService.getMedicalRecordById(id);
		return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
	}
	
	@GetMapping("/patientId")
	public ResponseEntity<List<MedicalRecord>> getByPatientId(@RequestParam Long patientId){
			
		List<MedicalRecord> mc=medicalRecordService.findByPatientId(patientId);
		if(mc.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mc, HttpStatus.OK);
	}


	
}
