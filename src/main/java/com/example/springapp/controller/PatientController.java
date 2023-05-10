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

import com.example.springapp.model.Patient;
import com.example.springapp.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping
	public ResponseEntity<Patient> save(@RequestBody Patient patient){
			
		patientService.savePatient(patient);
		return new ResponseEntity<>(patient, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Patient> update(@RequestBody Patient patient){
			
		patientService.updatePatient(patient);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam Long id){
			
		patientService.deletePatient(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<Patient>> getAll(){
			
		List <Patient> patient=patientService.getAllPatient();
		return new ResponseEntity<> (patient, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Optional<Patient>> getById(@RequestParam Long id){
			
		Optional<Patient> patient=patientService.getPatientById(id);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	

}
