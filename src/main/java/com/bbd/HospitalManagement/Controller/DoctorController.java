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

import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@PostMapping("/add")
	public DoctorDetails addDoctor(@RequestBody DoctorDetails doctor) {
		return doctorService.saveDoctor(doctor);
	}

	@GetMapping("/getall")
	public List<DoctorDetails> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@GetMapping("/get/{id}")
	public DoctorDetails getDoctorById(@PathVariable("id") Long id) {
		return doctorService.getDoctorById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteDoctor(@PathVariable("id") Long id) {
		doctorService.deleteDoctor(id);
		return "Doctor deleted with id: " + id;
	}
}
