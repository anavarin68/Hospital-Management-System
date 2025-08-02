package com.bbd.HospitalManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Model.UserRole;

public interface DoctorService {

	DoctorDetails registerDoctor(DoctorDetails doctor);

	DoctorDetails updateDoctor(Long id, DoctorDetails updatedDoctor);

	void deleteDoctorById(Long id);

	List<DoctorDetails> getAllDoctors();

	Optional<DoctorDetails> getDoctorById(Long id);

	Optional<DoctorDetails> findByEmail(String email);

	Optional<DoctorDetails> findByEmailAndRole(String email, UserRole role);
}
