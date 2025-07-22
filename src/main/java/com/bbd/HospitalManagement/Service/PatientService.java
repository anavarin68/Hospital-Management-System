package com.bbd.HospitalManagement.Service;

import java.util.List;

import com.bbd.HospitalManagement.Model.PatientDetails;

public interface PatientService {
	
	PatientDetails savePatient(PatientDetails patient);
	List<PatientDetails> getAllPatients();
    PatientDetails getPatientById(Long id);
    void deletePatient(Long id);
}
