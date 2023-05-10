package com.example.springapp.service;

import java.util.List;
import java.util.Optional;


import com.example.springapp.model.Appointment;

public interface AppointmentService {
	
	 public String saveAppointment(Appointment appointment);
	 public String updateAppointment( Appointment appointment);
	 public String deleteAppointment(Long id);
	 public List<Appointment> getAllAppointment();
	 public Optional<Appointment> getAppointmentById(Long id);
	 public List<Appointment> getAppointmentByPatientId(Long patientId);
	 public List<Appointment> getAppointmentByDoctorId(Long doctorId);



}
