package com.bbd.HospitalManagement.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DoctorDetails {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
	@Column
	private String specialization;
	@Column
	private String contact;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<AppointmentDetails> appointments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<AppointmentDetails> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentDetails> appointments) {
		this.appointments = appointments;
	}

	public DoctorDetails(String name, String specialization, String contact, String email, String password,
			UserRole role, List<AppointmentDetails> appointments) {
		super();
		this.name = name;
		this.specialization = specialization;
		this.contact = contact;
		this.email = email;
		this.password = password;
		this.role = role;
		this.appointments = appointments;
	}

	public DoctorDetails() {
		super();
	}
}
