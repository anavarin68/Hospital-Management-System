package com.bbd.HospitalManagement.ServiceImpl;

import com.bbd.HospitalManagement.Model.AppointmentDetails;
import com.bbd.HospitalManagement.Repository.AppointmentRepository;
import com.bbd.HospitalManagement.Service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public AppointmentDetails saveAppointment(AppointmentDetails appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<AppointmentDetails> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public AppointmentDetails getAppointmentById(Long id) {
		return appointmentRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public List<AppointmentDetails> getAppointmentsByPatientId(Long patientId) {
		return appointmentRepository.findByPatientId(patientId);
	}

	@Override
	public List<AppointmentDetails> getAppointmentsByDoctorId(Long doctorId) {
		return appointmentRepository.findByDoctorId(doctorId);
	}

}
