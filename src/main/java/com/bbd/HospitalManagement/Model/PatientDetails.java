package com.bbd.HospitalManagement.Model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
public class PatientDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@DateTimeFormat(pattern = "dd-MM-yyyy") // important for form binding
    private LocalDate dob;

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

	public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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
	
	public int getAge() {
	    if (this.dob == null) return 0; 
	    return Period.between(this.dob, LocalDate.now()).getYears();
	}
	
	public PatientDetails(String name, LocalDate dob, String gender, String contact, String email, String password,
			UserRole role, List<AppointmentDetails> appointments) {
		super();
		this.name = name;
		this.dob = dob;
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
