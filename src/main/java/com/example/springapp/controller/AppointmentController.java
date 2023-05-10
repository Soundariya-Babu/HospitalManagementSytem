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

import com.example.springapp.model.Appointment;
import com.example.springapp.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/")
	public ResponseEntity<String> save(@RequestBody Appointment appointment){
			
		String s=appointmentService.saveAppointment(appointment);
		if(s.equals("Appointment booked")) {
			return new ResponseEntity<>(s, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}
	
	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody Appointment appointment){
			
		String s=appointmentService.updateAppointment(appointment);
		if(s.equals("Appointment booked")) {
			return new ResponseEntity<>(s, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> delete(@RequestParam Long id){
			
		String s=appointmentService.deleteAppointment(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity <List<Appointment>> getAll(){
			
		List <Appointment> appointments=appointmentService.getAllAppointment();
		return new ResponseEntity<> (appointments, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Optional<Appointment>> getById(@RequestParam Long id){
			
		Optional<Appointment> appointment=appointmentService.getAppointmentById(id);
		if(appointment.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
	@GetMapping("/doctorId")
	public ResponseEntity<List<Appointment>> getByDoctorId(@RequestParam Long doctorId){
			
		List<Appointment> appointment=appointmentService.getAppointmentByDoctorId(doctorId);
		if(appointment.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
	@GetMapping("/patientId")
	public ResponseEntity<List<Appointment>> getByPatientId(@RequestParam Long patientId){
			
		List<Appointment> appointment=appointmentService.getAppointmentByPatientId(patientId);
		if(appointment.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
	
	

}
