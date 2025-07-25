package com.bbd.HospitalManagement.Controller;

import com.bbd.HospitalManagement.Model.AppointmentDetails;
import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Model.UserRole;
import com.bbd.HospitalManagement.Service.AppointmentService;
import com.bbd.HospitalManagement.Service.DoctorService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;


	// GET: Show doctor registration form
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("doctor", new DoctorDetails());
		return "auth/doctor-register"; // corresponds to templates/auth/doctor-register.html
	}

	// POST: Handle doctor registration
	@PostMapping("/register")
	public String registerDoctor(@ModelAttribute DoctorDetails doctor, Model model) {
		doctor.setRole(UserRole.DOCTOR); // set the role explicitly
		doctorService.registerDoctor(doctor);
		model.addAttribute("success", "Doctor registered successfully!");
		return "redirect:/doctor/login";
	}

	// GET: Show doctor login form
	@GetMapping("/login")
	public String showLoginForm() {
		return "auth/doctor-login"; // corresponds to templates/auth/doctor-login.html
	}

	// POST: Handle doctor login
	@PostMapping("/login")
	public String loginDoctor(@RequestParam String email, @RequestParam String password, Model model,
			HttpSession session) {
		return doctorService.findByEmailAndRole(email, UserRole.DOCTOR)
				.filter(doc -> doc.getPassword().equals(password)).map(doc -> {
					session.setAttribute("doctorId", doc.getId()); // Save doctor ID in session
					return "redirect:/doctor/dashboard";
				}).orElseGet(() -> {
					model.addAttribute("error", "Invalid email or password");
					return "auth/doctor-login";
				});
	}

	@GetMapping("/appointments")
	public String viewDoctorAppointments(Model model, HttpSession session) {
		Long doctorId = (Long) session.getAttribute("doctorId");
		if (doctorId == null)
			return "redirect:/doctor/login";

		List<AppointmentDetails> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
		model.addAttribute("appointments", appointments);
		return "doctor/doctor-view-appointments";
	}

	@PostMapping("/appointments/{id}/complete")
	public String markAppointmentCompleted(@PathVariable Long id, HttpSession session) {
		Long doctorId = (Long) session.getAttribute("doctorId");
		if (doctorId == null)
			return "redirect:/doctor/login";

		AppointmentDetails appointment = appointmentService.getAppointmentById(id);
		if (appointment != null && appointment.getDoctor().getId().equals(doctorId)) {
			appointment.setStatus("Completed");
			appointmentService.saveAppointment(appointment);
		}
		return "redirect:/doctor/appointments";
	}

	@PostMapping("/appointments/{id}/delete")
	public String deleteAppointment(@PathVariable Long id, HttpSession session) {
		Long doctorId = (Long) session.getAttribute("doctorId");
		if (doctorId == null)
			return "redirect:/doctor/login";

		AppointmentDetails appointment = appointmentService.getAppointmentById(id);
		if (appointment != null && appointment.getDoctor().getId().equals(doctorId)) {
			appointmentService.deleteAppointment(id);
		}
		return "redirect:/doctor/appointments";
	}


	@GetMapping("/dashboard")
	public String doctorDashboard(HttpSession session, Model model) {
	    Long doctorId = (Long) session.getAttribute("doctorId");
	    if (doctorId == null) {
	        return "redirect:/doctor/login";
	    }

	    doctorService.getDoctorById(doctorId).ifPresent(doctor -> model.addAttribute("doctor", doctor));
	    return "doctor/doctor-dashboard"; // templates/doctor/doctor-dashboard.html
	}


}
