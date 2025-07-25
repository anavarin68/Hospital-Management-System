package com.bbd.HospitalManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbd.HospitalManagement.Model.DoctorDetails;
import com.bbd.HospitalManagement.Model.UserRole;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetails, Long> {
	Optional<DoctorDetails> findByEmail(String email);

	Optional<DoctorDetails> findByEmailAndRole(String email, UserRole role);
}