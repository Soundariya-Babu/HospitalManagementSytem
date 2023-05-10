package com.example.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Medical_Record")
public class MedicalRecord {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="patient_id")
	private Long patientId;
	
	@Column(name="doctor_id")
	private Long doctorId;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="diagnosis")
	private String diagnosis;
	
	@Column(name="prescription")
	private String prescription;
	
	@Column(name="notes")
	private String notes;
	
	

	public MedicalRecord() {
	}

	public MedicalRecord(Long id, Long patientId, Long doctorId, LocalDate date, String diagnosis, String prescription,
			String notes) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.diagnosis = diagnosis;
		this.prescription = prescription;
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatient_id() {
		return patientId;
	}

	public void setPatient_id(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctor_id() {
		return doctorId;
	}

	public void setDoctor_id(Long doctor_id) {
		this.doctorId = doctor_id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
