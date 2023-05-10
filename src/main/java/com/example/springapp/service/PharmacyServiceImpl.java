package com.example.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Pharmacy;
import com.example.springapp.repository.PharmacyRepository;

@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
	private PharmacyRepository pharmacyRepo;

	@Override
	public void savePharmacy(Pharmacy p) {
		pharmacyRepo.save(p);
	}

	@Override
	public void updatePharmacy(Pharmacy p) {
		if(pharmacyRepo.existsById(p.getId()))
		{
		pharmacyRepo.save(p);
		}
	}

	@Override
	public void deletePharmacy(Long id) {
		pharmacyRepo.deleteById(id);
	}

	@Override
	public List<Pharmacy> getAllPharmacy() {
		List<Pharmacy>	pharmacy=pharmacyRepo.findAll();
		return pharmacy;
	}

	@Override
	public Optional<Pharmacy> getPharmacyById(Long id) {
		Optional<Pharmacy> pharmacy=pharmacyRepo.findById(id);
		return pharmacy;
	}

	@Override
	public List<Pharmacy> findByPatientId(Long patientId) {
		List<Pharmacy>	pharmacy=pharmacyRepo.findByPatientId(patientId);
		return pharmacy;
	}

}
