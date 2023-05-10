package com.example.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Billing;
import com.example.springapp.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired
	private BillingService billingService;
	
	@PostMapping
	public ResponseEntity<Billing> save(@RequestBody Billing billing){
			
		billingService.saveBilling(billing);
		return new ResponseEntity<>(billing, HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity <List<Billing>> getAll(){
			
		List <Billing> bills=billingService.getAllBilling();
		return new ResponseEntity<> (bills, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Optional<Billing>> getById(Long id){
			
		Optional<Billing> billing=billingService.getBillingById(id);
		if(billing.isPresent())
		{
			return new ResponseEntity<>(billing, HttpStatus.OK);
		}
		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		
	
	

	@GetMapping("/patientId")
	public ResponseEntity<List<Billing>> getByPatientId(Long patientId){
			
		List<Billing> billing=billingService.findByPatientId(patientId);
		if(billing.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(billing, HttpStatus.OK);
	}


}
