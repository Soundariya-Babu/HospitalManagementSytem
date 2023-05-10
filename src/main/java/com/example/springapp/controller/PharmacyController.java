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

import com.example.springapp.model.Pharmacy;
import com.example.springapp.service.PharmacyService;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;
	
	@PostMapping
	public ResponseEntity<Pharmacy> save(@RequestBody Pharmacy pharmacy){
			
		pharmacyService.savePharmacy(pharmacy);
		return new ResponseEntity<>(pharmacy, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Pharmacy> update(@RequestBody Pharmacy pharmacy){
			
		pharmacyService.updatePharmacy(pharmacy);
		return new ResponseEntity<>(pharmacy, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam Long id){
			
		pharmacyService.deletePharmacy(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<Pharmacy>> getAll(){
			
		List <Pharmacy> pharmacy=pharmacyService.getAllPharmacy();
		return new ResponseEntity<> (pharmacy, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Optional<Pharmacy>> getById(@RequestParam Long id){
			
		Optional<Pharmacy> pharmacy=pharmacyService.getPharmacyById(id);
		return new ResponseEntity<>(pharmacy, HttpStatus.OK);
	}
	
	@GetMapping("/patientId")
	public ResponseEntity<List<Pharmacy>> getByPatientId(@RequestParam Long patientId){
			
		List<Pharmacy> pharmacy=pharmacyService.findByPatientId(patientId);
		if(pharmacy.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pharmacy, HttpStatus.OK);
	}

	
}
