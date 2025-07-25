package com.bbd.HospitalManagement.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Model.UserRole;
import com.bbd.HospitalManagement.Repository.PatientRepository;
import com.bbd.HospitalManagement.Service.PatientService;

@Service
public class PatientServiceImplementation implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Optional<PatientDetails> findByEmail(String email) {
		return patientRepository.findByEmail(email);
	}

	@Override
	public Optional<PatientDetails> findByEmailAndRole(String email, UserRole role) {
		return patientRepository.findByEmailAndRole(email, role);
	}

	@Override
	public PatientDetails registerPatient(PatientDetails patient) {
		return patientRepository.save(patient);
	}

	@Override
	public List<PatientDetails> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
    public Optional<PatientDetails> getPatientById(Long id) {
        return patientRepository.findById(id);
    }
}
