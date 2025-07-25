package com.bbd.HospitalManagement.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Model.UserRole;
import com.bbd.HospitalManagement.Repository.DoctorRepository;
import com.bbd.HospitalManagement.Service.DoctorService;

@Service
public class DoctorServiceImplementation implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Optional<DoctorDetails> findByEmail(String email) {
		return doctorRepository.findByEmail(email);
	}

	@Override
	public Optional<DoctorDetails> findByEmailAndRole(String email, UserRole role) {
		return doctorRepository.findByEmailAndRole(email, role);
	}

	@Override
	public DoctorDetails registerDoctor(DoctorDetails doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public List<DoctorDetails> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public Optional<DoctorDetails> getDoctorById(Long id) {
		return doctorRepository.findById(id);
	}
}
