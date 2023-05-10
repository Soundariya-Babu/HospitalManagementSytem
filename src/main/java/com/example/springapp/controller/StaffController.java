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

import com.example.springapp.model.Staff;
import com.example.springapp.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@PostMapping
	public ResponseEntity<Staff> save(@RequestBody Staff staff){
			
		staffService.saveStaff(staff);
		return new ResponseEntity<>(staff, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Staff> update(@RequestBody Staff staff){
			
		staffService.updateStaff(staff);
		return new ResponseEntity<>(staff, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam Long id){
			
		staffService.deleteStaff(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity <List<Staff>> getAll(){
			
		List <Staff> staff=staffService.getAllStaff();
		return new ResponseEntity<> (staff, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Optional<Staff>> getById(Long id){
			
		Optional<Staff> staff=staffService.getStaffById(id);
		return new ResponseEntity<>(staff, HttpStatus.OK);
	}
	
	@GetMapping("/jobTitle")
	public ResponseEntity<List<Staff>> getByJobTitle(@RequestParam String jobTitle){
			
		List<Staff> staffs=staffService.getStaffByJobTitle(jobTitle);
		if(staffs.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(staffs, HttpStatus.OK);
	}

}
