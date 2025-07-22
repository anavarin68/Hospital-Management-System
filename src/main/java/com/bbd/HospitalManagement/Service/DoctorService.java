package com.bbd.HospitalManagement.Service;

import java.util.List;

import com.bbd.HospitalManagement.Model.DoctorDetails;

public interface DoctorService {

	DoctorDetails saveDoctor(DoctorDetails doctor);

	List<DoctorDetails> getAllDoctors();

	DoctorDetails getDoctorById(Long id);

	void deleteDoctor(Long id);

}
