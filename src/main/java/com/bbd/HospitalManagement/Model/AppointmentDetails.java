package com.bbd.HospitalManagement.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class AppointmentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime appointmentDateTime;

	@Column
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorDetails doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientDetails patient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DoctorDetails getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDetails doctor) {
		this.doctor = doctor;
	}

	public PatientDetails getPatient() {
		return patient;
	}

	public void setPatient(PatientDetails patient) {
		this.patient = patient;
	}

	public AppointmentDetails(LocalDateTime appointmentDateTime, String status, DoctorDetails doctor,
			PatientDetails patient) {
		super();
		this.appointmentDateTime = appointmentDateTime;
		this.status = status;
		this.doctor = doctor;
		this.patient = patient;
	}

	public AppointmentDetails() {
		super();
	}

}
