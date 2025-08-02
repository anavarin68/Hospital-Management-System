package com.bbd.HospitalManagement.ServiceImpl;

import com.bbd.HospitalManagement.Model.AppointmentDetails;
import com.bbd.HospitalManagement.Repository.AppointmentRepository;
import com.bbd.HospitalManagement.Service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<AppointmentDetails> searchAppointments(String doctorName, String patientName, String date,
			String time) {
		return appointmentRepository.findAll().stream().filter(appointment -> {
			String status = appointment.getStatus();
			return status != null && (status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("completed"));
		}).filter(appointment -> doctorName == null
				|| appointment.getDoctor().getName().toLowerCase().contains(doctorName.toLowerCase()))
				.filter(appointment -> patientName == null
						|| appointment.getPatient().getName().toLowerCase().contains(patientName.toLowerCase()))
				.filter(appointment -> {
					if (date == null)
						return true;
					try {
						return appointment.getAppointmentDateTime().toLocalDate().toString().equals(date);
					} catch (Exception e) {
						return false;
					}
				}).filter(appointment -> {
					if (time == null)
						return true;
					try {
						return appointment.getAppointmentDateTime().toLocalTime().toString().equals(time);
					} catch (Exception e) {
						return false;
					}
				}).collect(Collectors.toList());
	}

}
