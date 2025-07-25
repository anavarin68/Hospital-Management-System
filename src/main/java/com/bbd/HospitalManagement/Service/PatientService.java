package com.bbd.HospitalManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Model.UserRole;

public interface PatientService {

	Optional<PatientDetails> findByEmail(String email);

	Optional<PatientDetails> findByEmailAndRole(String email, UserRole role);

	PatientDetails registerPatient(PatientDetails patient);

	List<PatientDetails> getAllPatients();

	Optional<PatientDetails> getPatientById(Long id);
}
