package com.example.springapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Appointment")
public class Appointment {
	
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
	
	@Column(name="time")
	private LocalTime time;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="status")
	private String status;
	
	

	public Appointment(Long patientId, Long doctorId, LocalDate date, LocalTime time, String duration, String status) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.status = status;
	}
	public Appointment() {}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patientId=" + patientId + ", doctorId=" + doctorId + ", date=" + date
				+ ", time=" + time + ", duration=" + duration + ", status=" + status + "]";
	}

	public Appointment(Long id, Long patientId, Long doctorId, LocalDate date, LocalTime time, String duration,
			String status) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
