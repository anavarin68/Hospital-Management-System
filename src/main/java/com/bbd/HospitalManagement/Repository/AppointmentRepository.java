package com.bbd.HospitalManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbd.HospitalManagement.Model.AppointmentDetails;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentDetails, Long> {
	List<AppointmentDetails> findByPatientId(Long patientId);
	List<AppointmentDetails> findByDoctorId(Long doctorId);

}
