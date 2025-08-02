package com.bbd.HospitalManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Model.UserRole;

public interface PatientService {

	PatientDetails registerPatient(PatientDetails patient);

	PatientDetails updatePatient(Long id, PatientDetails updatedPatient);

	void deletePatientById(Long id);

	List<PatientDetails> getAllPatients();

	Optional<PatientDetails> getPatientById(Long id);

	Optional<PatientDetails> findByEmail(String email);

	Optional<PatientDetails> findByEmailAndRole(String email, UserRole role);
}
