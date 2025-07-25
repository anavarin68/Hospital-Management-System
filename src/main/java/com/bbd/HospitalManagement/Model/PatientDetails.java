package com.bbd.HospitalManagement.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class PatientDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private Integer age;

	@Column
	private String gender;

	@Column
	private String contact;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public PatientDetails(String name, Integer age, String gender, String contact, String email, String password,
			UserRole role, List<AppointmentDetails> appointments) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.contact = contact;
		this.email = email;
		this.password = password;
		this.role = role;
		this.appointments = appointments;
	}

	public PatientDetails() {
		super();
	}
}
