package com.bbd.HospitalManagement.Service;

import java.util.List;

import com.bbd.HospitalManagement.Model.AppointmentDetails;

public interface AppointmentService {

	AppointmentDetails saveAppointment(AppointmentDetails appointment);

	void deleteAppointment(Long id);

	List<AppointmentDetails> getAllAppointments();

	AppointmentDetails getAppointmentById(Long id);

	List<AppointmentDetails> getAppointmentsByPatientId(Long patientId);

	List<AppointmentDetails> getAppointmentsByDoctorId(Long doctorId);
	
	public List<AppointmentDetails> searchAppointments(String doctorName, String patientName, String date, String time);
}
