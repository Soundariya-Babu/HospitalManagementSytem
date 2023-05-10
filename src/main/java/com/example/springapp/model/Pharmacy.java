package com.example.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Pharmacy")
public class Pharmacy {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="patient_id")
	private Long patientId;
	
	@Column(name="medication_name")
	private String medication_name;
	
	@Column(name="dosage")
	private String dosage;
	
	@Column(name="refill_date")
	private LocalDate refill_date;
	
	@Column(name="prescription_number")
	private Long prescription_number;
	
	

	public Pharmacy() {
	}

	public Pharmacy(Long id, Long patientId, String medication_name, String dosage, LocalDate refill_date,
			Long prescription_number) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.medication_name = medication_name;
		this.dosage = dosage;
		this.refill_date = refill_date;
		this.prescription_number = prescription_number;
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

	public void setPatient_id(Long patient_id) {
		this.patientId = patient_id;
	}

	public String getMedication_name() {
		return medication_name;
	}

	public void setMedication_name(String medication_name) {
		this.medication_name = medication_name;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public LocalDate getRefill_date() {
		return refill_date;
	}

	public void setRefill_date(LocalDate refill_date) {
		this.refill_date = refill_date;
	}

	public Long getPrescription_number() {
		return prescription_number;
	}

	public void setPrescription_number(Long prescription_number) {
		this.prescription_number = prescription_number;
	}
	
	
	
}
