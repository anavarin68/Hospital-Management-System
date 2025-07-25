package com.bbd.HospitalManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Model.UserRole;

public interface DoctorService {

	Optional<DoctorDetails> findByEmail(String email);

    Optional<DoctorDetails> findByEmailAndRole(String email, UserRole role);

    DoctorDetails registerDoctor(DoctorDetails doctor);

    List<DoctorDetails> getAllDoctors();

    Optional<DoctorDetails> getDoctorById(Long id);
   
}
