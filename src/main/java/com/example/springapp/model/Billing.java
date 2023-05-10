package com.example.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Billing")
public class Billing {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="patient_id")
	private Long patientId;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="treatment_description")
	private String treatment_description;
	
	@Column(name="amount")
	private Long amount;
	
	

	public Billing() {
	}

	public Billing(Long id, Long patientId, LocalDate date, String treatment_description, Long amount) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.date = date;
		this.treatment_description = treatment_description;
		this.amount = amount;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTreatment_descrption() {
		return treatment_description;
	}

	public void setTreatment_descrption(String treatment_descrption) {
		this.treatment_description = treatment_descrption;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	

}
