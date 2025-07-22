package com.bbd.HospitalManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/add")
	public PatientDetails addPatient(@RequestBody PatientDetails patient) {
		return patientService.savePatient(patient);
	}
	
	@GetMapping("/getall")
	public List<PatientDetails> getAllPatients() {
		return patientService.getAllPatients();
	}

	@GetMapping("/get/{id}")
	public PatientDetails getPatientById(@PathVariable("id") Long id) {
		return patientService.getPatientById(id);	
	}
	
	@DeleteMapping("/delete/{id}")
	public String deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatient(id);
		return "Patient deleted with id: " + id;
	}
}
