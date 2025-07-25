package com.bbd.HospitalManagement.Controller;

import com.bbd.HospitalManagement.Model.AppointmentDetails;
import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Service.AppointmentService;
import com.bbd.HospitalManagement.Service.DoctorService;
import com.bbd.HospitalManagement.Service.PatientService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	// ============================
	// 🔹 Web (Thymeleaf) Endpoints
	// ============================

	// Show form to book a new appointment
	@GetMapping("/new")
	public String showAppointmentForm(Model model, HttpSession session) {
		Long patientId = (Long) session.getAttribute("patientId");
		if (patientId == null)
			return "redirect:/patient/login";

		model.addAttribute("appointment", new AppointmentDetails());
		model.addAttribute("doctors", doctorService.getAllDoctors());
		return "patient/patient-new-appointment";
	}

	// Submit the new appointment
	@PostMapping("/new")
	public String saveAppointment(@ModelAttribute AppointmentDetails appointment,
	                              @RequestParam Long doctorId,
	                              HttpSession session) {
	    Long patientId = (Long) session.getAttribute("patientId");
	    if (patientId == null) return "redirect:/patient/login";

	    DoctorDetails doctor = doctorService.getDoctorById(doctorId).orElse(null);
	    PatientDetails patient = patientService.getPatientById(patientId).orElse(null);

	    if (doctor == null || patient == null) {
	        return "redirect:/patient/dashboard";
	    }

	    appointment.setDoctor(doctor);
	    appointment.setPatient(patient);
	    appointment.setStatus("Pending");

	    appointmentService.saveAppointment(appointment);
	    return "redirect:/patient/dashboard";
	}


	// View all appointments for current patient
	@GetMapping("/patient")
	public String viewPatientAppointments(Model model, HttpSession session) {
		Long patientId = (Long) session.getAttribute("patientId");
		if (patientId == null)
			return "redirect:/patient/login";

		List<AppointmentDetails> appointments = appointmentService.getAppointmentsByPatientId(patientId);
		model.addAttribute("appointments", appointments);
		return "patient/patient-view-appointments";
	}

	// ============================
	// 🔹 REST API Endpoints (JSON)
	// ============================

	@PostMapping("/api/add")
	@ResponseBody
	public AppointmentDetails addAppointment(@RequestBody AppointmentDetails appointment) {
		return appointmentService.saveAppointment(appointment);
	}

	@GetMapping("/api/getall")
	@ResponseBody
	public List<AppointmentDetails> getAllAppointments() {
		return appointmentService.getAllAppointments();
	}

	@GetMapping("/api/get/{id}")
	@ResponseBody
	public AppointmentDetails getAppointmentById(@PathVariable Long id) {
		return appointmentService.getAppointmentById(id);
	}

	@DeleteMapping("/api/delete/{id}")
	@ResponseBody
	public String deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
		return "Deleted appointment with id: " + id;
	}

	@PutMapping("/api/update-status/{id}")
	@ResponseBody
	public AppointmentDetails updateStatus(@PathVariable Long id) {
		AppointmentDetails appointment = appointmentService.getAppointmentById(id);
		if (appointment == null) {
			throw new RuntimeException("Appointment not found with id: " + id);
		}
		appointment.setStatus("Completed");
		return appointmentService.saveAppointment(appointment);
	}
	
	@PostMapping("/delete/{id}")
	public String deleteAppointmentFromPatient(@PathVariable Long id, HttpSession session) {
	    Long patientId = (Long) session.getAttribute("patientId");
	    if (patientId == null) return "redirect:/patient/login";

	    AppointmentDetails appt = appointmentService.getAppointmentById(id);
	    if (appt != null && appt.getPatient().getId().equals(patientId)) {
	        appointmentService.deleteAppointment(id);
	    }
	    return "redirect:/appointments/patient";
	}

}
