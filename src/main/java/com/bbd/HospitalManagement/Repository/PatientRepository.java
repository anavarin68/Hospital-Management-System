package com.bbd.HospitalManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbd.HospitalManagement.Model.PatientDetails;
import com.bbd.HospitalManagement.Model.UserRole;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetails, Long> {
	Optional<PatientDetails> findByEmail(String email);
	Optional<PatientDetails> findByEmailAndRole(String email, UserRole role);
}
