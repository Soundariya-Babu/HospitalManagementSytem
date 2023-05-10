package com.example.springapp.service;

import java.util.List;
import java.util.Optional;


import com.example.springapp.model.Pharmacy;

public interface PharmacyService {
	 
	 public void savePharmacy(Pharmacy p);
	 public void updatePharmacy( Pharmacy p);
	 public void deletePharmacy(Long id);
	 public List<Pharmacy> getAllPharmacy();
	 public Optional<Pharmacy> getPharmacyById(Long id);
		public List<Pharmacy> findByPatientId(Long patientId);


}
