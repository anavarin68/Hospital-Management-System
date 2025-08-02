package com.bbd.HospitalManagement.Controller;

import com.bbd.HospitalManagement.Model.AppointmentDetails;
import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Model.UserRole;
import com.bbd.HospitalManagement.Service.AppointmentService;
import com.bbd.HospitalManagement.Service.DoctorService;

import jakarta.servlet.http.HttpSession;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
		return "auth/doctor-register";
	}

	// POST: Handle doctor registration
	@PostMapping("/register")
	public String registerDoctor(@ModelAttribute DoctorDetails doctor, Model model) {
		doctor.setRole(UserRole.DOCTOR);
		doctorService.registerDoctor(doctor);
		model.addAttribute("success", "Doctor registered successfully!");
		return "redirect:/doctor/login";
	}

	// GET: Show doctor login form
	@GetMapping("/login")
	public String showLoginForm() {
		return "auth/doctor-login";
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

	//Get all appointments
	@GetMapping("/appointments")
	public String viewDoctorAppointments(Model model, HttpSession session) {
		Long doctorId = (Long) session.getAttribute("doctorId");
		if (doctorId == null)
			return "redirect:/doctor/login";

		List<AppointmentDetails> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
		model.addAttribute("appointments", appointments);
		return "doctor/doctor-view-appointments";
	}
	
	//Update appointments status
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
	
	//Delete an appointment
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
	
	//View Patients
	@GetMapping("/patients")
	public String viewPatients(Model model, HttpSession session) {
	    Long doctorId = (Long) session.getAttribute("doctorId");
	    if (doctorId == null)
	        return "redirect:/doctor/login";

	    List<AppointmentDetails> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);

	    Set<PatientDetails> uniquePatients = appointments.stream()
	        .map(AppointmentDetails::getPatient)
	        .collect(Collectors.toCollection(LinkedHashSet::new));

	    model.addAttribute("patients", uniquePatients);
	    return "doctor/doctor-patients";
	}

	//Doctor Dashboard
	@GetMapping("/dashboard")
	public String doctorDashboard(HttpSession session, Model model) {
	    Long doctorId = (Long) session.getAttribute("doctorId");
	    if (doctorId == null) {
	        return "redirect:/doctor/login";
	    }

	    Optional<DoctorDetails> optionalDoctor = doctorService.getDoctorById(doctorId);
	    if (optionalDoctor.isPresent()) {
	        DoctorDetails doctor = optionalDoctor.get();
	        model.addAttribute("doctor", doctor);

	        String fullName = doctor.getName();
	        if (fullName != null && !fullName.isEmpty()) {
	            String firstName = fullName.split(" ")[0];
	            model.addAttribute("firstName", firstName);
	        } else {
	            model.addAttribute("firstName", "Doctor");
	        }
	    } else {
	        return "redirect:/doctor/login";
	    }

	    return "doctor/doctor-dashboard";
	}
}
