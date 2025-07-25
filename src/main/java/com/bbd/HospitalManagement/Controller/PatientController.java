package com.bbd.HospitalManagement.Controller;

import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Model.UserRole;
import com.bbd.HospitalManagement.Service.PatientService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	// GET: Show doctor registration form
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("patient", new PatientDetails());
		return "auth/patient-register"; // Correct path
	}
	
	// POST: Handle doctor registration
	@PostMapping("/register")
	public String registerPatient(@ModelAttribute PatientDetails patient, Model model) {
		patient.setRole(UserRole.PATIENT); // Set the role
		patientService.registerPatient(patient);
		model.addAttribute("success", "Patient registered successfully!");
		return "redirect:/patient/login";
	}
	
	// GET: Show patient login form
	@GetMapping("/login")
	public String showLoginForm() {
		return "auth/patient-login"; // matches templates/auth/patient-login.html
	}
	
	// POST: Handle doctor login
	@PostMapping("/login")
	public String loginPatient(@RequestParam String email, @RequestParam String password, Model model,
			HttpSession session) {
		return patientService.findByEmailAndRole(email, UserRole.PATIENT).filter(p -> p.getPassword().equals(password))
				.map(p -> {
					session.setAttribute("patientId", p.getId()); // ✅ store patient ID
					return "redirect:/patient/dashboard";
				}).orElseGet(() -> {
					model.addAttribute("error", "Invalid email or password");
					return "auth/patient-login";
				});
	}

	//Patient Dashboard
	@GetMapping("/dashboard")
	public String patientDashboard(HttpSession session, Model model) {
	    Long patientId = (Long) session.getAttribute("patientId");
	    if (patientId == null) return "redirect:/patient/login";

	    patientService.getPatientById(patientId)
	                  .ifPresent(patient -> model.addAttribute("patient", patient));
	    
	    return "patient/patient-dashboard";
	}

}
