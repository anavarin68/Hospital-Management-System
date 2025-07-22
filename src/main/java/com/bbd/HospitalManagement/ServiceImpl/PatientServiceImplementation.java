package com.bbd.HospitalManagement.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Repository.PatientRepository;
import com.bbd.HospitalManagement.Service.PatientService;

@Service
public class PatientServiceImplementation implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public PatientDetails savePatient(PatientDetails patient) {
		return patientRepository.save(patient);
	}
	
	@Override
	public List<PatientDetails> getAllPatients() {
		return patientRepository.findAll();
	}
	
	@Override
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}
	
	@Override
	public PatientDetails getPatientById(Long id) {
		return patientRepository.findById(id).get();
	}
}
