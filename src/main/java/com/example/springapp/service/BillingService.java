package com.example.springapp.service;

import java.util.List;
import java.util.Optional;


import com.example.springapp.model.Billing;

public interface BillingService {
	
	 public void saveBilling(Billing billing);
	 public List<Billing> getAllBilling();
	 public Optional<Billing> getBillingById(Long id);
	public List<Billing> findByPatientId(Long patientId);


}
