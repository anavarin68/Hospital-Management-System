package com.bbd.HospitalManagement.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbd.HospitalManagement.Model.AppointmentDetails;
import com.bbd.HospitalManagement.Repository.AppointmentRepository;
import com.bbd.HospitalManagement.Service.AppointmentService;

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
	public void deleteAppointment(long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public AppointmentDetails getAppointmentById(Long id) {
		return appointmentRepository.findById(id).get();
	}
}
