package com.example.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Staff;
import com.example.springapp.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepo;
	
	@Override
	public void saveStaff(Staff staff) {
		staffRepo.save(staff);
	}

	@Override
	public void updateStaff(Staff staff) {
		if(staffRepo.existsById(staff.getId()))
		{
			staffRepo.save(staff);
	    }	
		
	}	

	@Override
	public void deleteStaff(Long id) {
		staffRepo.deleteById(id);
	}

	@Override
	public List<Staff> getAllStaff() {
		List<Staff> staffs=staffRepo.findAll();		
		return staffs;
	}

	@Override
	public Optional<Staff> getStaffById(Long id) {
		Optional<Staff> staff=staffRepo.findById(id);		
		return staff;
	}
	
	@Override
	public List<Staff> getStaffByJobTitle(String jobTitle)
	{
		List<Staff> staffs=staffRepo.findByJobTitle(jobTitle);
		return staffs;
		
	}

}
