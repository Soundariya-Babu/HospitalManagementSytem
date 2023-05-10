package com.example.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Billing;
import com.example.springapp.repository.BillingRepository;

@Service
public class BillingServiceImpl implements BillingService {
	
	@Autowired
	private BillingRepository billingRepo;

	@Override
	public void saveBilling(Billing billing) {
		billing.setDate(LocalDate.now());
		billingRepo.save(billing);
	}

	@Override
	public List<Billing> getAllBilling() {
		List<Billing> billingDetails= billingRepo.findAll();
		return billingDetails;
	}

	@Override
	public Optional<Billing> getBillingById(Long id) {
		if(billingRepo.existsById(id)) {
			Optional<Billing> billing=billingRepo.findById(id);
			return billing;
		}
		return null;
	}

	@Override
	public List<Billing> findByPatientId(Long patientId) {
		List<Billing> billingDetails= billingRepo.findByPatientId(patientId);		
		return billingDetails;
	}

}
