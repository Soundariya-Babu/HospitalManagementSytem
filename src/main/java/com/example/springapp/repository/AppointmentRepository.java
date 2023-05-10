package com.example.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springapp.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>  {
	
	public List<Appointment> findByPatientId(Long patientId);
	public List<Appointment> findByDoctorId(Long doctorId);

}