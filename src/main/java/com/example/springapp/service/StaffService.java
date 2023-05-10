package com.example.springapp.service;

import java.util.List;
import java.util.Optional;


import com.example.springapp.model.Staff;

public interface StaffService {
	
	 public void saveStaff(Staff staff);
	 public void updateStaff( Staff staff);
	 public void deleteStaff(Long id);
	 public List<Staff> getAllStaff();
	 public Optional<Staff> getStaffById(Long id);
	 public List<Staff> getStaffByJobTitle(String JobTitle);

}
