package com.bbd.HospitalManagement.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Repository.DoctorRepository;
import com.bbd.HospitalManagement.Service.DoctorService;

@Service
public class DoctorServiceImplementation implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public DoctorDetails saveDoctor(DoctorDetails doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public List<DoctorDetails> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

	@Override
	public DoctorDetails getDoctorById(Long id) {
		return doctorRepository.findById(id).get();
	}
}
