package com.bbd.HospitalManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.HospitalManagement.Model.AppointmentDetails;
import com.bbd.HospitalManagement.Service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;

	@PostMapping("/add")
	public AppointmentDetails addAppointment(@RequestBody AppointmentDetails appointment) {
		return appointmentService.saveAppointment(appointment);
	}

	@GetMapping("/getall")
	public List<AppointmentDetails> getAllAppointments() {
		return appointmentService.getAllAppointments();
	}

	@GetMapping("/get/{id}")
	public AppointmentDetails getAppointmentById(@PathVariable Long id) {
		return appointmentService.getAppointmentById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
		return "Appointment deleted with id:" + id;
	}

	@PutMapping("/update/{id}")
	public AppointmentDetails markAppointmentStatus(@PathVariable Long id) {
		AppointmentDetails appointment = appointmentService.getAppointmentById(id);
		if (appointment != null) {
			appointment.setStatus("Completed");
			return appointmentService.saveAppointment(appointment);
		} else {
			throw new RuntimeException("Appointment not found with id: " + id);
		}

	}

}
