package com.bbd.HospitalManagement.Controller;

import com.bbd.HospitalManagement.Model.*;
import com.bbd.HospitalManagement.Service.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private AppointmentService appointmentService;

	//  LOGIN 
	
	@GetMapping("/login")
	public String showAdminLoginPage(Model model) {
		return "auth/admin-login";
	}

	@PostMapping("/login")
	public String loginAdmin(@RequestParam String name, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
		if ("admin".equals(name) && "Nitish@8968".equals(password)) {
			session.setAttribute("adminName", name);
			return "redirect:/admin/admin-dashboard";
		} else {
			redirectAttributes.addFlashAttribute("error", "Invalid Admin Credentials");
	        return "auth/admin-login";
		}
	}

	@GetMapping("/admin-dashboard")
	public String adminDashboard() {
		return "admin/admin-dashboard";
	}

	// DOCTOR MANAGEMENT

	@GetMapping("/doctors")
	public String viewDoctors(Model model) {
		List<DoctorDetails> doctors = doctorService.getAllDoctors();
		model.addAttribute("doctors", doctors);
		return "admin/admin-doctors";
	}

	@GetMapping("/doctors/add")
	public String showAddDoctorForm(Model model) {
		model.addAttribute("doctor", new DoctorDetails());
		return "admin/admin-add-doctor";
	}

	@PostMapping("/doctors/add")
	public String addDoctor(@ModelAttribute DoctorDetails doctor) {
		doctor.setRole(UserRole.DOCTOR);
		doctorService.registerDoctor(doctor);
		return "redirect:/admin/doctors";
	}

	@GetMapping("/doctors/edit/{id}")
	public String showEditDoctorForm(@PathVariable Long id, Model model) {
		Optional<DoctorDetails> doctorOptional = doctorService.getDoctorById(id);
		if (doctorOptional.isPresent()) {
			model.addAttribute("doctor", doctorOptional.get());
			return "doctor/edit-doctor";
		} else {
			model.addAttribute("error", "Doctor not found");
			return "redirect:/admin/doctors";
		}
	}

	@PostMapping("/doctors/update/{id}")
	public String updateDoctor(@PathVariable Long id, @ModelAttribute DoctorDetails doctor) {
		doctorService.updateDoctor(id, doctor);
		return "redirect:/admin/doctors";
	}

	@GetMapping("/doctors/delete/{id}")
	public String deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctorById(id);
		return "redirect:/admin/doctors";
	}

	// PATIENT MANAGEMENT 

	@GetMapping("/patients")
	public String viewPatients(Model model) {
		List<PatientDetails> patients = patientService.getAllPatients();
		model.addAttribute("patients", patients);
		return "admin/admin-patients";
	}

	@GetMapping("/patients/add")
	public String showAddPatientForm(Model model) {
		model.addAttribute("patient", new PatientDetails());
		return "admin/admin-add-patient";
	}

	@PostMapping("/patients/add")
	public String addPatient(@ModelAttribute PatientDetails patient) {
		patient.setRole(UserRole.PATIENT);
		patientService.registerPatient(patient);
		return "redirect:/admin/patients";
	}

	@GetMapping("/patients/edit/{id}")
	public String showEditPatientForm(@PathVariable Long id, Model model) {
		Optional<PatientDetails> patientOptional = patientService.getPatientById(id);
		if (patientOptional.isPresent()) {
			model.addAttribute("patient", patientOptional.get());
			return "patient/edit-patient";
		} else {
			model.addAttribute("error", "Patient not found");
			return "redirect:/admin/patients";
		}
	}

	@PostMapping("/patients/update/{id}")
	public String updatePatient(@PathVariable Long id, @ModelAttribute PatientDetails patient) {
		patientService.updatePatient(id, patient);
		return "redirect:/admin/patients";
	}

	@GetMapping("/patients/delete/{id}")
	public String deletePatient(@PathVariable Long id) {
		patientService.deletePatientById(id);
		return "redirect:/admin/patients";
	}

	// APPOINTMENT MANAGEMENT

	@GetMapping("/appointments")
	public String viewAppointments(Model model) {
		List<AppointmentDetails> appointments = appointmentService.getAllAppointments();
		model.addAttribute("appointments", appointments);
		return "admin/admin-appointments";
	}

	@GetMapping("/appointments/search")
	public String searchAppointments(@RequestParam(required = false) String doctorName,
			@RequestParam(required = false) String patientName, @RequestParam(required = false) String date,
			@RequestParam(required = false) String time, Model model) {

		if (doctorName != null && doctorName.trim().isEmpty())
			doctorName = null;
		if (patientName != null && patientName.trim().isEmpty())
			patientName = null;
		if (date != null && date.trim().isEmpty())
			date = null;
		if (time != null && time.trim().isEmpty())
			time = null;

		List<AppointmentDetails> results = appointmentService.searchAppointments(doctorName, patientName, date, time);
		model.addAttribute("appointments", results);
		return "admin/admin-appointments";
	}

}
