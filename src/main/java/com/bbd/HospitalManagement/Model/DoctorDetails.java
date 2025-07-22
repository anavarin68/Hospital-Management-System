package com.bbd.HospitalManagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DoctorDetails {

	@Id
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String specialization;
	@Column
	private String contact;

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

	public DoctorDetails(Long id, String name, String specialization, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.specialization = specialization;
		this.contact = contact;
	}

	public DoctorDetails() {
		super();
	}

}
